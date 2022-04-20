package com.db.herviz.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.User;
import com.db.herviz.mapper.UserMapper;
import com.db.herviz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean isLogin() {
        return StpUtil.isLogin();
    }
}
