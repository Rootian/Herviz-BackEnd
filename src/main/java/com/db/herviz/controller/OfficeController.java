package com.db.herviz.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.herviz.domain.RequireLogin;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Office;
import com.db.herviz.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-25
 */
@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getOfficeList(String keywords, Integer page, Integer limit) {
        Page<Office> officeList = officeService.getOfficeList(keywords, page, limit);
        return ResponseX.page(officeList.getRecords(), officeList.getTotal());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOffice(@RequestBody String body) {
        Office office = JSONObject.parseObject(body, Office.class);
        try {
            officeService.updateById(office);
        } catch (Exception e) {
            return ResponseX.fail("update office fail");
        }
        return ResponseX.success(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOffice(@RequestBody String body) {
        Office office = JSONObject.parseObject(body, Office.class);
        try {
            officeService.save(office);
        } catch (Exception e) {
            return ResponseX.fail("add office fail");
        }
        return ResponseX.success(null);
    }
}
