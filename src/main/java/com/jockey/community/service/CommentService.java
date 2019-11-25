package com.jockey.community.service;

import com.jockey.community.dto.CommentDTO;
import com.jockey.community.enums.CommentType;
import com.jockey.community.exception.CustomizeErrorCode;
import com.jockey.community.exception.CustomizeException;
import com.jockey.community.mapper.*;
import com.jockey.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentExtendMapper commentExtendMapper;


    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionExtendMapper questionExtendMapper;

    @Autowired
    UserMapper userMapper;


    @Transactional
    public Comment addComment(Comment comment){

        if(comment.getType() == null)
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_TYPE);

        if(!CommentType.isExist(comment.getType()))
            throw new CustomizeException(CustomizeErrorCode.COMMENT_WRONG_TYPE);

        if(comment.getParentId() == null)
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_PARENT_ID);

        if((comment.getType() == CommentType.QUESTION.getType() && questionMapper.selectByPrimaryKey(comment.getParentId()) == null)
        || (comment.getType() == CommentType.COMMENT.getType() && commentMapper.selectByPrimaryKey(comment.getParentId()) == null))
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_PARENT_ID);

        commentMapper.insertSelective(comment);
        if(comment.getType() == CommentType.QUESTION.getType()){
            Question question = new Question();
            question.setId(comment.getParentId());
            question.setCommentCount(1);

            questionExtendMapper.increaseCommentCount(question);
        }else if(comment.getType() == CommentType.COMMENT.getType()){
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1L);

            commentExtendMapper.increaseCommentCount(parentComment);
        }


        return comment;
    }


    public List<CommentDTO> listByTargetId(Long id, CommentType type) {

        //获取该提问的评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");

        List<Comment> comments = commentMapper.selectByExample(commentExample);


        if(comments.size() == 0)
            return new ArrayList<>();
        else{

            //使用set过滤重复评论创建者
            Set<Long> commentCreators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
            List<Long> userIds = new ArrayList<>();
            //转换为list
            userIds.addAll(commentCreators);

            //获取评论的创建者
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdIn(userIds);
            List<User> users = userMapper.selectByExample(userExample);

            Map<Long,User> userMap = users.stream().collect(Collectors.toMap(user->user.getId(),user->user));

            //转换 comment 为 commentDTO
            List<CommentDTO> commentDTOS = comments.stream().map(
                    comment -> {
                        CommentDTO commentDTO = new CommentDTO();
                        BeanUtils.copyProperties(comment,commentDTO);
                        commentDTO.setUser(userMap.get(comment.getCommentator()));
                        return commentDTO;
                    }
            ).collect(Collectors.toList());

            return commentDTOS;
        }
    }
}
