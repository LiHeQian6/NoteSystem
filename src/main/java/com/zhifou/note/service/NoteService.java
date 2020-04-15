package com.zhifou.note.service;

import com.zhifou.entity.Note;
import com.zhifou.entity.NoteType;
import com.zhifou.note.repository.NoteRepository;
import com.zhifou.note.repository.TypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: NoteSystem
 * @description: note
 * @author: 景光赞
 * @create: 2020-04-10 12:34
 **/
@Service
@Transactional(readOnly = true)
public class NoteService {
    @Resource
    private NoteRepository noteRepository;
    @Resource
    private TypeRepository typeRepository;

    public Note findNoteById(int noteId){
        return noteRepository.findNoteById(noteId);
    }
    @Transactional(readOnly = false)
    public int addNote(Note note){
        return noteRepository.save(note).getId();
    }
    //查询所有笔记
    public Page<Note> finAllNotes(Pageable pageable){
        return noteRepository.findAll(pageable);
    }
    //分类查询(type)
    public Page<Note> findNoteByType(int type,Pageable pageable){
        return noteRepository.findNoteByType(type,pageable);
    }
    //查询笔记一级标题(二级标题可根据一级获取)
    public List<NoteType> findOneLevel(){
        return typeRepository.findAllByParentTypeIsNull();
    }
    //查询点赞最多20个笔记
    public Page<Note> findTop20Note(Pageable pageable){
        return noteRepository.findTop20ByOrderByLikeNumDesc(pageable);
    }

}


