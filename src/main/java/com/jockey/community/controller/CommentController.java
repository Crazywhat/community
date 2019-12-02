package com.jockey.community.controller;

import com.jockey.community.dto.CommentCreateDTO;
import com.jockey.community.dto.CommentDTO;
import com.jockey.community.dto.ResultDTO;
import com.jockey.community.enums.CommentType;
import com.jockey.community.exception.CustomizeErrorCode;
import com.jockey.community.exception.CustomizeException;
import com.jockey.community.model.Comment;
import com.jockey.community.model.User;
import com.jockey.community.service.CommentService;
import com.jockey.community.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    NotificationService notificationService;


    @ResponseBody
    @PostMapping("/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) {
            log.error("comment: no login.");
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || commentCreateDTO.getContent() == null || commentCreateDTO.getContent().isEmpty())
        {
            log.error("comment: content is empty.");
            throw new CustomizeException(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentCreateDTO,comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);

        commentService.addComment(comment);

        //创建新的通知
        notificationService.addNotification(user,comment.getType(),comment.getParentId());

        log.trace("comment: comment commit successful.");

        return ResultDTO.okOf();
    }




    @ResponseBody
    @RequestMapping("/comment/{id}")
    public ResultDTO<List<CommentDTO>> getSubComment(@PathVariable("id")Long id){
        List<CommentDTO> commentDTOs = commentService.listByTargetId(id, CommentType.COMMENT);
        log.trace("comment: get sub-comment.");
        return ResultDTO.okOf(commentDTOs);
    }



}
