package com.zhifou.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: li
 * Date: 2020/4/14
 * Time: 15:24
 */
@Entity
@Table
public class Notification {
    private int id;
    private User from;
    private User to;
    private NotificationType notificationType;
    private Date createTime;
    private Note aboutNote;
    private boolean haveRead;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Note getAboutNote() {
        return aboutNote;
    }

    public void setAboutNote(Note aboutNote) {
        this.aboutNote = aboutNote;
    }

    public boolean isHaveRead() {
        return haveRead;
    }

    public void setHaveRead(boolean haveRead) {
        this.haveRead = haveRead;
    }
}
