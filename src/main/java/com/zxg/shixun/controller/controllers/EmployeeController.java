package com.zxg.shixun.controller.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxg.shixun.common.R;
import com.zxg.shixun.entity.Employee;
import com.zxg.shixun.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request , @RequestBody Employee employee){
//        1.将页面密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        根据页面提供的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

//      3.r如果没有查到，返回登录失败结果
        if(emp==null){
            return R.error("登陆失败");
        }
//        4.检查密码
        if(!emp.getPassword().equals(password)){
            return R.error("登陆失败,密码不对");
        }
        if(emp.getStatus()==0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
    @PostMapping("/logout")
    public R<String> logOut(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("成功退出");
    }
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());
        //设置初始密码，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        Long empId = (Long) request.getSession().getAttribute("employee");
//
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息");
        Employee employee = employeeService.getById(id);
        if(employee!=null){
            return R.success(employee);

        }
        return R.error("未查询到信息");
    }
}
