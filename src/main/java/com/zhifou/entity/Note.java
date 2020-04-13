package com.zhifou.entity;

import javax.persistence.*;

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
}
