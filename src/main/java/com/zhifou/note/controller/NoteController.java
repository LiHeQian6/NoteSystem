package com.zhifou.note.controller;

import com.zhifou.entity.Comment;
import com.zhifou.entity.Note;
import com.zhifou.entity.NoteType;
import com.zhifou.note.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * @Description 跳转到登录页
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
        int pageNum = 0;
        if(num != 0) {
            pageNum = num;
        }
        return noteService.findNoteByType(id, PageRequest.of(pageNum, 9));
    }

    @RequestMapping("editNote")
    public String edit_note(){
        return "edit_note";
    }

    @RequestMapping("management")
    public String toManagement(){
        return "management";
    }
    /**
    * @Description: 笔记详情页
    * @Param:
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/14
    */
    @RequestMapping("/detail")
    public String toDetail(@RequestParam("noteId")int id, Model model){
        model.addAttribute(noteService.findNoteById(id));
        return "detail";
    }
    /**
     * @description: 首页展示
     * @author :景光赞
     * @date :2020/4/15 11:53
     * @param :[num, model]
     * @return :java.lang.String
     */
    @RequestMapping("/index")
    public String toIndex(@RequestParam("pageNum")int num,Model model){
        int pageNum = 0;
        if(num != 0) {
            pageNum = num;
        }
        model.addAttribute(noteService.findTop20Note(PageRequest.of(pageNum, 9)));
        return "index";
    }
    /**
     * @description:  按关键字搜索相关笔记
     * @author :景光赞
     * @date :2020/4/15 17:01
     * @param :[num, word]
     * @return :org.springframework.data.domain.Page<com.zhifou.entity.Note>
     */
    @ResponseBody
    @RequestMapping("/findNoteLike")
    public Page<Note> findRelativeNote(@RequestParam("pageNum")int num,@RequestParam("word")String word){
        int pageNum = 0;
        if(num != 0){
            pageNum = num;
        }
        return noteService.findNoteLike("%"+word+"%",PageRequest.of(pageNum, 9));
    }

    /**
     * @description:
     * @author :景光赞
     * @date :2020/4/16 11:55
     * @param :[noteId]
     * @return :java.util.List<com.zhifou.entity.Comment>
     */
    @ResponseBody
    @RequestMapping("/top3Comment")
    public List<Comment> findTop3Comment(@RequestParam("noteId")int noteId){
        return noteService.findTop3Comment(noteService.findNoteById(noteId));
    }
    @ResponseBody
    @RequestMapping("/top3Comment2")
    public List<Comment> findTop3Comment2(){
        return noteService.findTop3Comment(noteService.findNoteById(1));
    }
}
