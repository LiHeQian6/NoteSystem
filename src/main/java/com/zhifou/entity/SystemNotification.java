package com.zhifou.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: li
 * Date: 2020/4/13
 * Time: 15:54
 */
@Entity
@Table(name = "system_notification")
public class SystemNotification {
    private int id;
    private String title;
    private String content;
    private Date createTime;
    private User user;//当用户为null时代表该条通知是发送给所有人的

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_notification_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
