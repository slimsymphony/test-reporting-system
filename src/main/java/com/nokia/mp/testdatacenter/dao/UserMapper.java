/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.mp.testdatacenter.dao;

import org.springframework.stereotype.Repository;

import com.nokia.mp.testdatacenter.model.User;

/**
 *
 * @author larryang
 */
@Repository("userMapper")
public interface UserMapper {
    
    public void insertUser(User user);

    public User getUser(String name);
}
