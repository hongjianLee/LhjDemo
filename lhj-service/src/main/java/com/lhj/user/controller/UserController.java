package com.lhj.user.controller;


import com.lhj.lhjcore.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("get/{id}")
    public String get(@PathVariable Long id){

        Map<String, Object> map = new HashMap<>(2);
        map.put("id",id);
        return userService.listByMap(map).toString();
    }
}
