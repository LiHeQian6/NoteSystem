package com.zhifou.user.repository;
/**
 * @program: zhifou
 * @description: userRepository
 * @author: 景光赞
 * @create: 2020-04-07 20:53
 **/
import com.zhifou.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>, Serializable {

    User findUserByAccountAndPassword(String account,String password);

    User findUserById(int id);

    User findUserByAccount(String account);
    @Query(value = "select u.* from user u,notes n where n.type_id = ? and n.author_id = u.user_id limit 5",nativeQuery = true)
    List<User> findRelativeUsers(int typeId);
}
