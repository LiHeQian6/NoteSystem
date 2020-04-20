package com.zhifou.notification.controller;

import com.zhifou.entity.SystemNotification;
import com.zhifou.entity.User;
import com.zhifou.notification.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * User: li
 * Date: 2020/4/14
 * Time: 11:02
 */
@Controller
@RequestMapping("notification")
public class NotificationController {

    @Resource
    private NotificationService notificationService;

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到消息通知页
     * @Date 11:38 2020/4/14
     **/
    @RequestMapping("/")
    public String toNotification(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, HttpServletRequest request){
        if (pageNum==0){
            pageNum=1;
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "login";
        }
        //TODO 获取所有通知的数据存储到model中，且将用户字段的最近读取系统通知时间修改为现在
        List<SystemNotification> systemNotifications=notificationService.getSystemNotification(user.getId(),pageNum);
        request.setAttribute("systemNotification",systemNotifications);
        return "notification";
    }

    /**
     * @Author li
     * @param request
     * @return java.lang.String
     * @Description 获取新消息数
     * @Date 11:39 2020/4/14
     **/
    @ResponseBody
    @RequestMapping("newNotificationNum")
    public String getNewNotificationNum(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null)
            return "请先登录";
        //TODO 统计在用户收到的新消息数 包括系统消息和其他消息的数量
        return "";
    }

    /**
     * @Author li
     * @param systemNotification
     * @return java.lang.String
     * @Description 给所有用户发送系统消息,用于后台管理系统发布通知公告等
     * @Date 14:44 2020/4/14
     **/
    @ResponseBody
    @RequestMapping(value = "sendSystemNotification",method = RequestMethod.POST)
    public String sendSystemNotification(SystemNotification systemNotification){
        if (notificationService.sendSystemNotification(systemNotification)) {
            return "true";
        }
        return "false";
    }

}
