package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.R;
import com.example.reggie.domain.Category;
import com.example.reggie.domain.Employee;
import com.example.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> list(int page,int pageSize){
        Page page1 = new Page(page, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(page1, queryWrapper);
        return R.success(page1);
    }
    @GetMapping("/list")
    public R<List<Category>> listNoPage(Category category){


        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort);

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
    @PostMapping
    public R<String> save (@RequestBody Category category){
        categoryService.save(category);
        return R.success("操作成功");
    }

    @DeleteMapping
    public R<String> delete(long ids) {
        categoryService.remove(ids);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("成功");
    }
}
