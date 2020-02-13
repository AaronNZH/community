package com.nzh.community.service;

import com.nzh.community.dto.NotificationDTO;
import com.nzh.community.dto.PaginationDTO;
import com.nzh.community.enums.NotificationStatusEnum;
import com.nzh.community.enums.NotificationTypeEnum;
import com.nzh.community.exception.CustomizeErrorCode;
import com.nzh.community.exception.CustomizeException;
import com.nzh.community.mapper.NotificationMapper;
import com.nzh.community.mapper.UserMapper;
import com.nzh.community.model.Notification;
import com.nzh.community.model.NotificationExample;
import com.nzh.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;


    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage = null;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount =  (int) notificationMapper.countByExample(notificationExample);

        if (totalCount == 0){
            totalPage = 1;
        }
        else if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }


        if(page < 1) {
            page = 1;
        }

        if(page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        if (offset < 0)
            offset = 0;
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notifications.size() != 0){
            List<NotificationDTO> notificationDTOS = new ArrayList<>();

            for (Notification notification : notifications) {
                NotificationDTO notificationDTO = new NotificationDTO();
                BeanUtils.copyProperties(notification, notificationDTO);
                notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
                notificationDTOS.add(notificationDTO);
            }
            paginationDTO.setData(notificationDTOS);
        }

        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!notification.getNotifier().equals(user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}