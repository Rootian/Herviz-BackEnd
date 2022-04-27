package com.db.herviz.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.entity.User;
import com.db.herviz.mapper.UserMapper;
import com.db.herviz.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

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

    @Override
    public SaTokenInfo login(String username, String password) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = getOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new BusinessException("username not found");
        }

        if (!checkPassword(user, password)) {
            throw new BusinessException("incorrect password");
        }
        // construct session id
        String sessionId = String.valueOf(System.currentTimeMillis()) + "_" + String.valueOf(user.getId()) + "_" + "user";

        StpUtil.login(sessionId);

        return StpUtil.getTokenInfo();
    }

    @Override
    public void passwordReset(String oldPassword, String newPassword) {
        String sessionId = StpUtil.getLoginIdAsString();
        Long uId = Long.valueOf(sessionId.split("_")[1]);
        User user = getById(uId);

        if (!checkPassword(user, oldPassword)) {
            throw new BusinessException("incorrect password");
        }
        String newMd5Pw = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        user.setPassword(newMd5Pw);
        updateById(user);
    }

    /**
     * @Description check if the password is right
     * @Author Rootian
     * @Date 2022-04-23
     * @param: user
     * @param: password
     * @return boolean
     */
    private boolean checkPassword(User user, String password) {
        String md5Pw = DigestUtils.md5DigestAsHex(password.getBytes());
        return user.getPassword().equals(md5Pw);
    }

    @Override
    public void register(User user) {
        String md5Pw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pw);
        save(user);
    }

    @Override
    public Page<User> getUserList(String keywords, Integer pageNum, Integer pageLimit) {
        QueryWrapper wrapper = new QueryWrapper();
        if (Strings.isNotBlank(keywords)) {
            wrapper.like("username", keywords);
        }
        Page<User> page = new Page<>(pageNum, pageLimit);
        baseMapper.selectPage(page, wrapper);
        return page;
    }
}
