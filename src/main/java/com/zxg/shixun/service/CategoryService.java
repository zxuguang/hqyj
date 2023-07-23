package com.zxg.shixun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxg.shixun.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
