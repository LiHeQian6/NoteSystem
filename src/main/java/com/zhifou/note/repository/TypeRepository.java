package com.zhifou.note.repository;

import com.zhifou.entity.NoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface TypeRepository extends JpaRepository<NoteType,Integer>, JpaSpecificationExecutor<NoteType>, Serializable {
    List<NoteType> findAllByParentTypeIsNull();

    NoteType findNoteTypeById(int id);
}
