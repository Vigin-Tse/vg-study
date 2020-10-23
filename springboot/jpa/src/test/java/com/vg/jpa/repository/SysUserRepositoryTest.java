package com.vg.jpa.repository;

import com.alibaba.fastjson.JSON;
import com.vg.jpa.domain.entity.UserDepartmentEntity;
import com.vg.jpa.domain.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description: 用户增删改查测试
 * @author: xieweij
 * @create: 2020-10-23 14:39
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserRepositoryTest {

    @Autowired
    private SysUserRepository userRepository;

    @Test
    public void findById(){
        Optional<SysUser> oUser = this.userRepository.findById(1);
        SysUser user = oUser.get();
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void findByUserName(){
        List<SysUser> users = this.userRepository.findByuserName("tony");
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    public void saveUser(){
        SysUser user = new SysUser();
        user.setUserName("jpa.new");
        user.setAge(23);
        user.setDeptId(1);

        user = this.userRepository.save(user);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void updateUser(){

        Optional<SysUser> oUser = this.userRepository.findById(40);
        if (oUser.get() != null){
            SysUser user = oUser.get();
            user.setUserName("jpa.update");
            user = this.userRepository.save(user);
            System.out.println(JSON.toJSONString(user));
        }
    }

    @Test
    public void deleteUser(){
        this.userRepository.deleteById(40);
    }

    @Test
    public void NativeQuery(){
        List<Map<String, Object>> entities = this.userRepository.getAllUserDepat();
        System.out.println(JSON.toJSONString(entities));
    }

    @Test
    public void userDepatQueryByJpql(){
        Optional<List<UserDepartmentEntity>> entities = this.userRepository.getAllUserDepatByJpql();
        System.out.println(JSON.toJSONString(entities.get()));
    }
}
