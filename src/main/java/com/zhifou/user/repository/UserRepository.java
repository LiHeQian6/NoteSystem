package com.zhifou.user.repository;

import com.zhifou.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>, Serializable {
//    @Query(value="from User where account = ?1 and password = ?2")
    User findByAccountAndPassword(String account,String password);

    List<User> findAll();

//    @Query(value = "from User where id = ?1")
    User findById(int id);

    @Query(value = "from User where account = ?1")
    User findUserByAccount(String account);

    @Override
    <S extends User> S save(S s);
}
