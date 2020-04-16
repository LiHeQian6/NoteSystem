package com.zhifou.note.repository;

import com.zhifou.entity.Comment;
import com.zhifou.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor<Comment>, Serializable {

    List<Comment> findTop3ByNoteOrderByLikeNumDesc(Note note);

    Page<Comment> findAllByNoteOrderByLikeNumDesc(Note note,Pageable pageable);

}
