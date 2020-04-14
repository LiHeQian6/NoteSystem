package com.zhifou.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: li
 * Date: 2020/4/14
 * Time: 15:26
 */
@Entity
@Table(name = "notification_type")
public class NotificationType {
    private int id;
    private String name;
    private Set<Notification> notifications=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_type_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "notificationType",fetch = FetchType.LAZY)
    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
}
