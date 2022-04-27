package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Office;
import com.db.herviz.mapper.OfficeMapper;
import com.db.herviz.service.OfficeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 19:19
 */
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper, Office> implements OfficeService {

    @Override
    public Page<Office> getOfficeList(String keywords, Integer page, Integer limit) {
        QueryWrapper<Office> wrapper = new QueryWrapper<>();
        if (Strings.isNotEmpty(keywords)) {
            // search keywords
        }
        Page<Office> pages = new Page<>(page, limit);
        baseMapper.selectPage(pages, wrapper);
        return pages;
    }
}
