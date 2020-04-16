package com.zhifou.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: NoteSystem
 * @description: 评论
 * @author: 景光赞
 * @create: 2020-04-15 18:17
 **/
@Entity
@Table(name = "note_comments")
@JsonIgnoreProperties(ignoreUnknown = true, value =
        {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Comment {
    private int id;
    private User user;
    private Note note;
    private Date time;
    private String content;
    private int likeNum;

    public Comment() {
    }

    public Comment(User user, Note note, Date time, String content) {
        this.user = user;
        this.note = note;
        this.time = time;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "note_id")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"comments"})
    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int like) {
        this.likeNum = like;
    }
}
