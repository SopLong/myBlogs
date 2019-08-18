package com.soplong.bolgs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soplong.bolgs.mapper.UserMapper;
import com.soplong.bolgs.pojo.system.User;
import com.soplong.bolgs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SopLong on 2019/7/28.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
}
