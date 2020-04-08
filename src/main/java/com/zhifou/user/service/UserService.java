package com.zhifou.user.service;
/**
 * @program: zhifou
 * @description: userService
 * @author: 景光赞
 * @create: 2020-04-07 20:53
 **/
import com.zhifou.entity.User;
import com.zhifou.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Resource
    private UserRepository userRepository;

    public User findByAccountAndPassword(String account, String password){
        System.out.println(password);
        return userRepository.findByAccountAndPassword(account,password);
    }
    public User findUserByAccount(String account){
        return userRepository.findUserByAccount(account);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(int id){
        return userRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public int regist(User user){
        return userRepository.save(user).getId();
    }
}
