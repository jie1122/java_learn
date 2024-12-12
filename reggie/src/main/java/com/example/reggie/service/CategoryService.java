package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.domain.Category;

public interface CategoryService extends IService<Category> {
    public void remove(long id);
}
