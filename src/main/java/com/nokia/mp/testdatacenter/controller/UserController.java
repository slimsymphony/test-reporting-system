package com.nokia.mp.testdatacenter.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nokia.mp.testdatacenter.utils.MyBatisUtil;
import com.nokia.mp.testdatacenter.dao.UserMapper;
import com.nokia.mp.testdatacenter.model.User;

/**
 *
 * @author larryang
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public @ResponseBody
    User findUser(@PathVariable String name) {
        
        System.out.println(name);
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.getUser(name);
            System.out.println("name: " + user.getName() + " | role: " + user.getRole());
        } finally {
            sqlSession.close();
        }
        
        return user;
    }

    @RequestMapping(value = "/add/{name}/{role}", method = RequestMethod.GET)
    public @ResponseBody
    User addUser(@PathVariable String name, @PathVariable String role) {
        
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = new User(name, role);
            userMapper.insertUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        
        return user;
    }
}