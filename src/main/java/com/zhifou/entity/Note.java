package com.zhifou.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: NoteSystem
 * @description: 笔记
 * @author: 景光赞
 * @create: 2020-04-10 10:56
 **/
@Entity
@Table(name="notes")
public class Note {
    private int id;
    private String title;
    private String content;
    private NoteType type;
    private User author;
    private int likeNum;
    private int lookNum;
    private Date createTime;
    private Set<Notification> notifications=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
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
    @ManyToOne
    @JoinColumn(name = "type_id")
    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }
    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getLookNum() {
        return lookNum;
    }

    public void setLookNum(int lookNum) {
        this.lookNum = lookNum;
    }

    @OneToMany(mappedBy = "aboutNote")
    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
}
