package com.zhifou.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

/**
 * @program: NoteSystem
 * @description: 笔记
 * @author: 景光赞
 * @create: 2020-04-10 10:56
 **/
@Entity
@Table(name="notes")
@JsonIgnoreProperties(ignoreUnknown = true, value =
        {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Note {
    private int id;
    private String title;
    private String content;
    private NoteType type;
    private User author;
    private int likeNum;
    private int lookNum;
    private Date createTime;
    private Set<Comment> comments = new HashSet<>();
    private Set<Notification> notifications=new HashSet<>();
    private int ifPush;

    public Note(int id) {
        this.id = id;
    }

    public Note() {
    }

    public Note(String title, String content, NoteType type, User author, Date createTime) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.author = author;
        this.createTime = createTime;
        this.ifPush=0;
    }

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
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"notes"})
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

    @OneToMany(mappedBy="note",targetEntity = Comment.class,cascade=CascadeType.ALL)
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"note"})
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "aboutNote")
    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public int getIfPush() {
        return ifPush;
    }

    public void setIfPush(int ifPush) {
        this.ifPush = ifPush;
    }
}
