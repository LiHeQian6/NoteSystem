package com.zhifou.notification.repository;

import com.zhifou.entity.SystemNotification;
import com.zhifou.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: li
 * Date: 2020/4/14
 * Time: 11:04
 */
public interface SystemNotificationRepository extends JpaRepository<SystemNotification,Integer> {
    List<SystemNotification> findSystemNotificationsByUserIs(User id);
}
