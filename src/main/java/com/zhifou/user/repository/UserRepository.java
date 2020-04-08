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
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>, Serializable {

    User findByAccountAndPassword(String account,String password);

    List<User> findAll();

    User findById(int id);

    User findUserByAccount(String account);

    @Override
    <S extends User> S save(S s);
}
