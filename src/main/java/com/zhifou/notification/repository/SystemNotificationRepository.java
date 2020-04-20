package com.zhifou.notification.repository;

import com.zhifou.entity.SystemNotification;
import com.zhifou.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: li
 * Date: 2020/4/14
 * Time: 11:04
 */
@Repository
public interface SystemNotificationRepository extends JpaRepository<SystemNotification,Integer> {
    @Query(value = "from SystemNotification where user=:user or (user is null and  createTime>:#{#user.createTime}) or createTime is null order by createTime desc ")
    List<SystemNotification> findSystemNotificationsByUser(@Param("user") User user);
}
