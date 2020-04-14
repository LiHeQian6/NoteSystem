package com.zhifou.note.controller;

import com.zhifou.entity.Note;
import com.zhifou.entity.NoteType;
import com.zhifou.note.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class NoteController {
    @Resource
    private NoteService noteService;

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到首页
     * @Date 8:54 2020/4/8
     **/
    @RequestMapping("/")
    public String toIndex(){
        return "login";
    }
    /**
    * @Description: 查询所有笔记类型
    * @Param: 
    * @return: 
    * @Author: 景光赞
    * @Date: 2020/4/10
    */
    @ResponseBody
    @RequestMapping("/type")
    public List<NoteType> findOneLevelType(){
        return noteService.findOneLevel();
    }
    /**
    * @Description: 分类查询笔记
    * @Param:
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/10
    */
    @ResponseBody
    @RequestMapping("findByType")
    public Page<Note> findByType(@RequestParam("typeId")int id,@RequestParam("pageNum")int num){
        int pageNum = 1;
        if(num != 0) {
            pageNum = num;
        }
        return noteService.findNoteByType(id, PageRequest.of(pageNum, 9));
    }

    @RequestMapping("editNote")
    public String edit_note(){
        return "edit_note";
    }
}
