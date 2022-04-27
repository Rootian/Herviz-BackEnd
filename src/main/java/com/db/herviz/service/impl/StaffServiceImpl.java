package com.db.herviz.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.entity.Staff;
import com.db.herviz.mapper.StaffMapper;
import com.db.herviz.service.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-27
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

    @Override
    public JSONObject login(String username, String password) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Staff staff = getOne(queryWrapper);

        if (Objects.isNull(staff)) {
            throw new BusinessException("username not found");
        }

        if (!checkPassword(staff, password)) {
            throw new BusinessException("incorrect password");
        }
        // construct session id
        String sessionId = String.valueOf(System.currentTimeMillis()) + "_" + String.valueOf(staff.getId()) + "_" + "staff";

        StpUtil.login(sessionId);

        JSONObject obj = new JSONObject();
        obj.put("token", StpUtil.getTokenValue());
        obj.put("uuid", StpUtil.getLoginId());
        obj.put("name", staff.getName());

        return obj;
    }

    @Override
    public void register(Staff staff) {
        String md5Pw = DigestUtils.md5DigestAsHex(staff.getPassword().getBytes());
        staff.setPassword(md5Pw);
        save(staff);
    }

    private boolean checkPassword(Staff staff, String password) {
        String md5Pw = DigestUtils.md5DigestAsHex(password.getBytes());
        return staff.getPassword().equals(md5Pw);
    }
}
