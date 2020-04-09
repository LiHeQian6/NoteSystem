package com.zhifou.user.repository;
/**
 * @program: zhifou
 * @description: userRepository
 * @author: 景光赞
 * @create: 2020-04-07 20:53
 **/
import com.zhifou.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>, Serializable {

    User findUserByAccountAndPassword(String account,String password);

    User findUserById(int id);

    User findUserByAccount(String account);

}
