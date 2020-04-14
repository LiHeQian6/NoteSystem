package com.zhifou.notification.repository;

import com.zhifou.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: li
 * Date: 2020/4/14
 * Time: 11:07
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
