package com.zhifou.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    private int id;
    private String account;
    private String nickName;
    private String password;
    private String photo;
    private String introduction;
    private Set<User> attentions = new HashSet<>();
    private Set<Note> notes = new HashSet<>();
    private Set<Note> collectNotes = new HashSet<>();

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @ManyToMany
    @JoinTable(name = "user_attention", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "attention_id"))
    public Set<User> getAttentions() {
        return attentions;
    }

    public void setAttentions(Set<User> attentions) {
        this.attentions = attentions;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @OneToMany(mappedBy="author", targetEntity=Note.class, cascade=CascadeType.ALL)
    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
    @ManyToMany
    @JoinTable(name = "user_collect_notes", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id"))
    public Set<Note> getCollectNotes() {
        return collectNotes;
    }

    public void setCollectNotes(Set<Note> collectNotes) {
        this.collectNotes = collectNotes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", attentions=" + attentions +
                '}';
    }
}
