package com.db.herviz.controller;

import com.db.herviz.domain.ResponseX;
import com.db.herviz.service.CorporateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-05-07
 */
@RestController
@RequestMapping("/api/corp")
public class CorporateController {


    @Autowired
    private CorporateService corporateService;

    @GetMapping("/list")
    public String getCorpList() {
        return ResponseX.success(corporateService.list());
    }
}
