package com.db.herviz.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.Staff;

public interface StaffService extends IService<Staff> {
    JSONObject login(String username, String password);

    void register(Staff staff);
}
