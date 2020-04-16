package com.zhifou.user.controller;

import com.zhifou.entity.Comment;
import com.zhifou.entity.User;
import com.zhifou.note.service.NoteService;
import com.zhifou.user.service.UserService;
import com.zhifou.util.MailUtil;
import com.zhifou.util.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private NoteService noteService;
    @Resource
    private VerifyUtil verifyUtil;

    @Resource
    private MailUtil mailUtil;

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到注册页
     * @Date 8:56 2020/4/8
     **/
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String toRegist(){
        return "regist";
    }

    /**
     * @Author li
     * @param account
     * @param password1
     * @param password2
     * @param vertical
     * @return java.lang.String
     * @Description 注册
     * @Date 9:26 2020/4/8
     **/
    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(@RequestParam(name = "account")String account,
                         @RequestParam(name = "password1")String password1,
                         @RequestParam(name = "password2")String password2,
                         @RequestParam("vertical")String vertical,HttpServletRequest request){
        HttpSession session = request.getSession();
        String orignVerify = (String) session.getAttribute("verifyCode");
        String email = (String) session.getAttribute("email");
        if(userService.findUserByAccount(account)==null){
            if(password1.equals(password2)){
                if(vertical.equals(orignVerify)&&account.equals(email)){
                    User user = new User(account, password1);
                    user.setCreateTime(new Date());
                    user.setLastReadTime(new Date());
                    if (userService.regist(user)!=0)
                        return "注册成功！";
                    return "注册失败";
                }
                return "验证码错误或已失效";
            }
            return "密码输入不一致";
        }
        return "邮箱已注册！";
    }

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到登录页
     * @Date 8:54 2020/4/8
     **/
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }
    
    /**
     * @Author 景光赞
     * @param account
     * @param password
     * @param request
     * @return java.lang.String
     * @Description
     * @Date 2020/4/7
     **/
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name = "account")String account,
                        @RequestParam(name = "password")String password, HttpServletRequest request){
        if(userService.findUserByAccount(account)!=null){
            User user = userService.findUserByAccountAndPassword(account, password);
            if(user !=null){
                request.getSession().setAttribute("user", user);
                return "登录成功！";
            }
            return "账号或密码错误！";
        }
        return "账号不存在，请先注册!";
    }
    /**
     * @Author 景光赞
     * @param
     * @return java.lang.String
     * @Description 用户列表展示
     * @Date 2020/4/7
     **/
    @ResponseBody
    @RequestMapping("/list")
    public void findAllUser(Model model){
        model.addAttribute("allUser",userService.findAll());
    }
    /**
    * @Description: 测试接口
    * @Param:
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/9
    */
    @ResponseBody
    @RequestMapping("/test")
    public User test(HttpServletRequest request){
        User user = userService.findUserById(1);
        user.getAttentions().remove(userService.findUserById(4));
        userService.updateUser(user);
        return user;
    }
    /**
    * @Description: 关注某人
    * @Param:
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/9
    */
    @ResponseBody
    @RequestMapping("/attention")
    public String attention(@RequestParam(name = "userId")int userId,
                            @RequestParam(name = "attentionId")int attentionId){
        User user = userService.findUserById(userId);
        user.getAttentions().add(userService.findUserById(attentionId));
        userService.updateUser(user);
        return "true";
    }
    /**
     * @Description: 取关某人
     * @Param:
     * @return:
     * @Author: 景光赞
     * @Date: 2020/4/9
     */
    @ResponseBody
    @RequestMapping("/getOff")
    public String getOff(@RequestParam(name = "userId")int userId,
                            @RequestParam(name = "attentionId")int attentionId){
        User user = userService.findUserById(userId);
        user.getAttentions().remove(userService.findUserById(attentionId));
        userService.updateUser(user);
        return "true";
    }
    /**
     * @Description: 收藏笔记
     * @Param:
     * @return:
     * @Author: 景光赞
     * @Date: 2020/4/9
     */
    @ResponseBody
    @RequestMapping("/collect")
    public String collectNote(@RequestParam(name = "userId")int userId,
                         @RequestParam(name = "noteId")int noteId){
        User user = userService.findUserById(userId);
        user.getNotes().add(noteService.findNoteById(noteId));
        userService.updateUser(user);
        return "true";
    }
    /**
     * @Description: 取消收藏笔记
     * @Param:
     * @return:
     * @Author: 景光赞
     * @Date: 2020/4/9
     */
    @ResponseBody
    @RequestMapping("/notCollect")
    public String removeNote(@RequestParam(name = "userId")int userId,
                              @RequestParam(name = "noteId")int noteId){
        User user = userService.findUserById(userId);
        user.getNotes().remove(noteService.findNoteById(noteId));
        userService.updateUser(user);
        return "true";
    }
    /**
     * @Description: 修改用户信息
     * @Param:      NickName,Photo,Introduction
     * @return:
     * @Author: 景光赞
     * @Date: 2020/4/9
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam(name = "userId")int userId,
                             @RequestParam(name = "nickName")String nickName,
                             @RequestParam(name = "photo")String photo,
                             @RequestParam(name = "introduction")String introduction){
        User user = userService.findUserById(userId);
        user.setNickName(nickName);
        user.setPhoto(photo);
        user.setIntroduction(introduction);
        userService.updateUser(user);
        return "true";
    }


    /**
     * @Author li
     * @param request
     * @return java.lang.String
     * @Description 注销登录
     * @Date 9:24 2020/4/8
     **/
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return "redirect:/";
    }

    /**
     * @Author li
     * @param email
     * @return java.lang.String
     * @Description 发送验证码
     * @Date 10:54 2020/4/8
     **/
    @RequestMapping("/getVerifyCode/{email}")
    @ResponseBody
    public String getVerifyCode(@PathVariable String email,HttpServletRequest request){
        HttpSession session = request.getSession();
        String verifyCode = verifyUtil.getVerify();
        session.setAttribute("verifyCode",verifyCode);//session 里存上发送验证码的email 验证验证码的时候验证邮箱是否统一
        session.setAttribute("email",email);
        mailUtil.sendVerifyCode(email,verifyCode);
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute("verifyCode");
                session.removeAttribute("email");
                timer.cancel();
            }
        },3*60*1000);
        return "true";
    }

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到忘记密码页
     * @Date 10:54 2020/4/8
     **/
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.GET)
    public String toForgetPassword(){
        return "forget_password";
    }

    /**
     * @Author li
     * @param account
     * @param verifyCode
     * @param request
     * @return java.lang.String
     * @Description 验证邮箱
     * @Date 11:04 2020/4/9
     **/
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    public String forgetPassword(@RequestParam("account")String account,@RequestParam("vertical")String verifyCode,HttpServletRequest request){
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("verifyCode");
        String email = (String) session.getAttribute("email");
        if (verifyCode.equals(code)&&account.equals(email)){
            session.setAttribute("changeEmail",account);
            return "change_password";
        }
        request.setAttribute("error","验证码错误");
        return "forget_password";
    }

    /**
     * @Author li
     * @param password1
     * @param password2
     * @return java.lang.String
     * @Description 修改密码
     * @Date 10:55 2020/4/8
     **/
    @ResponseBody
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public String changePassword(@RequestParam("password1")String password1,
                                 @RequestParam("password2")String password2,HttpServletRequest request){
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("changeEmail");
        if (password1.equals(password2)){
            if (email!=null&&!email.equals("")&&userService.changePassword(email,password1)) {
                session.setAttribute("changeEmail",null);
                return "修改成功";
            }
            return "修改失败";
        }
        return "两次输入密码不一致";
    }
    
    /**
     * @description: 展示相关学霸(笔记详情页)
     * @author :景光赞
     * @date :2020/4/15 17:35
     * @param :[typeId]
     * @return :java.util.List<com.zhifou.entity.User>
     */
    @ResponseBody
    @RequestMapping("/showRelative")
    public void showSomeUser(@RequestParam("typeId")int typeId,Model model){
        model.addAttribute("relative",userService.findRelativeUsers(typeId));
    }

    /**
     * @description: 写评论
     * @author :景光赞
     * @date :2020/4/16 15:34
     * @param :[noteId, userId, content]
     * @return :int
     */
    @ResponseBody
    @RequestMapping("/addComment")
    public int addComment(@RequestParam("noteId")int noteId,@RequestParam("userId")int userId,@RequestParam("content")String content){
        return noteService.addComment(new Comment(userService.findUserById(userId),noteService.findNoteById(noteId),
                new Date(),content));
    }
    /**
     * @description: 修改评论
     * @author :景光赞
     * @date :2020/4/16 15:35
     * @param :[noteId, userId, content]
     * @return :int
     */
    @ResponseBody
    @RequestMapping("/updateComment")
    public int updateComment(@RequestParam("noteId")int noteId,@RequestParam("userId")int userId,@RequestParam("content")String content){
        return noteService.addComment(new Comment(userService.findUserById(userId),noteService.findNoteById(noteId),
                new Date(),content));
    }
    /**
     * @description: 删除评论
     * @author :景光赞
     * @date :2020/4/16 15:35
     * @param :[noteId, userId, content]
     * @return :int
     */
    @ResponseBody
    @RequestMapping("/deleteComment")
    public int deleteComment(@RequestParam("noteId")int noteId,@RequestParam("userId")int userId,@RequestParam("content")String content){
        return noteService.addComment(new Comment(userService.findUserById(userId),noteService.findNoteById(noteId),
                new Date(),content));
    }

}
