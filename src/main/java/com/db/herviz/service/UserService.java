package com.db.herviz.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.entity.User;

public interface UserService extends IService<User> {
    boolean isLogin();

    JSONObject login(String username, String password) throws BusinessException;

    void passwordReset(String oldPassword, String newPassword) throws BusinessException;

    void register(User user);

    Page<User> getUserList(String keywords, Integer pageNum, Integer pageLimit);
}
