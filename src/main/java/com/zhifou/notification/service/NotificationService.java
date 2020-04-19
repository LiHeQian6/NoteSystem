package com.zhifou.notification.service;

import com.zhifou.entity.SystemNotification;
import com.zhifou.entity.User;
import com.zhifou.notification.repository.NotificationRepository;
import com.zhifou.notification.repository.SystemNotificationRepository;
import com.zhifou.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: li
 * Date: 2020/4/14
 * Time: 11:03
 */
@Service
@Transactional
public class NotificationService {

    @Resource
    private NotificationRepository notificationRepository;

    @Resource
    private SystemNotificationRepository systemNotificationRepository;

    @Resource
    private UserService userService;

    /**
     * @Author li
     * @param systemNotification
     * @return boolean
     * @Description 发送系统消息，包括所有用户的广播和发给指定用户
     * @Date 14:45 2020/4/14
     **/
    public boolean sendSystemNotification(SystemNotification systemNotification) {
        try {
            systemNotification.setCreateTime(new Date());
            systemNotificationRepository.save(systemNotification);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<SystemNotification> getSystemNotification(int id, int pageNum) {
        User user = userService.findUserById(id);
        return systemNotificationRepository.findSystemNotificationsByUser(user);
    }
}
