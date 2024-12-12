package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.R;
import com.example.reggie.domain.Category;
import com.example.reggie.domain.Dish;
import com.example.reggie.domain.DishFlavor;
import com.example.reggie.dto.DishDto;
import com.example.reggie.service.CategoryService;
import com.example.reggie.service.DishFlavorService;
import com.example.reggie.service.DishService;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private  DishFlavorService dishFlavorService;

    @Autowired
    private RedisTemplate redisTemplate ;
    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        dishService.saveWithFlavor(dishDto);
//        清理redis
        String key = "dish_"+dishDto.getCategoryId() ;
        redisTemplate.delete(key);
        return R.success("成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
//        菜品分页器
        Page<Dish> dishPage = new Page<>(page, pageSize);
//        构造菜品查询器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
//        查询菜品分页列表
        dishService.page(dishPage, queryWrapper);

//        菜品列表
        List<Dish> dishList = dishPage.getRecords();
        List<DishDto> dishDtoList = new ArrayList<>();
        dishList.forEach(e -> {
//            菜品属性赋值给菜品dto
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(e,dishDto);
//            查询菜品对应分类
//            LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
//            categoryLambdaQueryWrapper.eq(Category::getId, e.getCategoryId());
//            Category category = categoryService.getOne(categoryLambdaQueryWrapper);
            Category category = categoryService.getById(e.getCategoryId());
//分类名赋值给dto
            if (category != null) {
                dishDto.setCategoryName(category.getName());

            }
            dishDtoList.add(dishDto);
        });

        Page<DishDto> dishDtoPage = new Page<>();
        BeanUtils.copyProperties(dishPage,dishDtoPage,"records");
        dishDtoPage.setRecords(dishDtoList);
        return  R.success(dishDtoPage);
    }

    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        dishService.updateWithFlavor(dishDto);

//        清理redis
        String key = "dish_"+dishDto.getCategoryId() ;
        redisTemplate.delete(key);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<DishDto>> list (Dish dish) {
        List<DishDto> dishDtoList = null;
        String key = "dish_"+dish.getCategoryId() ;
        dishDtoList =  (List<DishDto>) redisTemplate.opsForValue().get(key);
        if (dishDtoList != null) {
            return R.success(dishDtoList);
        }
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        queryWrapper.eq( Dish::getStatus ,1);
        queryWrapper.orderByDesc(Dish::getSort);
        List<Dish> list = dishService.list(queryWrapper);
        List<DishDto> finalDishDtoList = new ArrayList<>();
        list.forEach(e -> {

            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(e,dishDto);
//            查询菜品对应分类
            LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, e.getId());
            List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorLambdaQueryWrapper);

            if (dishFlavorList != null) {
                dishDto.setFlavors(dishFlavorList);

            }
            finalDishDtoList.add(dishDto);
        });
        redisTemplate.opsForValue().set(key,finalDishDtoList,60, TimeUnit.MINUTES);
        return R.success(finalDishDtoList);
    }

}
