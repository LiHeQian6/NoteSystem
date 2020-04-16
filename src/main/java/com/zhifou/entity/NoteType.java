package com.zhifou.entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @program: NoteSystem
 * @description: 笔记类型
 * @author: 景光赞
 * @create: 2020-04-10 11:03
 **/

@Entity
@Table(name="note_type")
public class NoteType {
    private int id;
    private String name;
    private NoteType parentType;
    private Set<NoteType> types = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    public NoteType getParentType() {
        return parentType;
    }

    public void setParentType(NoteType parentType) {
        this.parentType = parentType;
    }

    @OneToMany(mappedBy = "parentType")
    public Set<NoteType> getTypes() {
        return types;
    }

    public void setTypes(Set<NoteType> types) {
        this.types = types;
    }
}

