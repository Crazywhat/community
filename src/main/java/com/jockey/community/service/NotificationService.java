package com.jockey.community.service;

import com.jockey.community.enums.CommentType;
import com.jockey.community.enums.NotificationStatus;
import com.jockey.community.enums.NotificationType;
import com.jockey.community.exception.CustomizeErrorCode;
import com.jockey.community.exception.CustomizeException;
import com.jockey.community.mapper.CommentMapper;
import com.jockey.community.mapper.NotificationMapper;
import com.jockey.community.mapper.QuestionMapper;
import com.jockey.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentMapper commentMapper;


    public Long getUnReadNotificationCount(Long userId){
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverIdEqualTo(userId)
                .andStatusEqualTo(NotificationStatus.UN_READ.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public Integer getSizeByReceiver(Long id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverIdEqualTo(id);
        return  (int) notificationMapper.countByExample(notificationExample);
    }

    public List<Notification> listByReceiver(Long id, Integer page, Integer size) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverIdEqualTo(id);
        notificationExample.setOrderByClause("gmt_create desc");

        return notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds((page - 1) * size, size));
    }

    public void addNotification(User sender, Integer commentType, Long commentParentId) {

        Notification notification = new Notification();
        notification.setSenderName(sender.getName());
        notification.setSenderId(sender.getId());
        notification.setStatus(NotificationStatus.UN_READ.getStatus());
        notification.setGmtCreate(System.currentTimeMillis());

        Question question = null;
        if(commentType == CommentType.QUESTION.getType()){
            question = questionMapper.selectByPrimaryKey(commentParentId);

            notification.setType(NotificationType.QUESTION_COMMENT.getInfo());
            notification.setReceiverId(question.getCreator());
        }else{
            Comment comment = commentMapper.selectByPrimaryKey(commentParentId);
            question = questionMapper.selectByPrimaryKey(comment.getParentId());

            notification.setType(NotificationType.COMMENT_COMMENT.getInfo());
            notification.setReceiverId(comment.getCommentator());
        }
        notification.setQuestionTitle(question.getTitle());
        notification.setQuestionId(question.getId());

        if(notification.getSenderId() != notification.getReceiverId())
            notificationMapper.insert(notification);
    }

    public Long Read(Long id) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null)
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NO_EXIST);

        notification.setStatus(NotificationStatus.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        return notification.getQuestionId();
    }
}
