package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.R;
import com.example.reggie.domain.Employee;
import com.example.reggie.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     *登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //密码md5加密
        String password = employee.getPassword();

        password =  DigestUtils.md5DigestAsHex(password.getBytes());

        //根据username查询
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        if (emp == null) {
            return R.error("登录失败");
        }
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
        }
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 添加用户
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> add (HttpServletRequest request, @RequestBody Employee employee){
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        long userId = (long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(userId);
//        employee.setUpdateUser(userId);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes() ));
        boolean ret = employeeService.save(employee);
        if (ret) {
            return  R.success("操作成功");

        }
        return  R.error("操作失败");

    }

    @GetMapping("/page")
    public R<Page> list(@RequestParam int page,  int pageSize,String name){
        // 分页构造器
        Page page1 = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序
        queryWrapper.orderByDesc(Employee::getCreateTime);

        employeeService.page(page1, queryWrapper);
        return R.success(page1);
    }


    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable long id){
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("未查到信息");
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {

        employeeService.updateById(employee);
        return R.success("修改成功");
    }
}
