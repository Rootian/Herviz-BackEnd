package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.User;

public interface UserService extends IService<User> {
    public boolean isLogin();
}
