package com.zhifou.note.repository;

import com.zhifou.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface NoteRepository  extends JpaRepository<Note,Integer>, JpaSpecificationExecutor<Note>, Serializable {
    Note findNoteById(int id);

    Page<Note> findAll(Pageable pageable);

    Page<Note> findNoteByType(int type,Pageable pageable);
}
