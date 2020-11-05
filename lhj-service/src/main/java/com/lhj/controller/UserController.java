package com.lhj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhj.lhjcore.IService.IUserService;
import com.lhj.lhjcore.entity.User;
import com.lhj.utils.redis.JedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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

    @Autowired
    private JedisTemplate jedisTemplate;

    @GetMapping("get/{id}")
    public String get(@PathVariable Long id) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        return userService.listByMap(map).toString();
    }

    @GetMapping("redis/{id}")
    public String redis(@PathVariable Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userService.getOne(wrapper);
        jedisTemplate.setString("user", user.getName(), 1000);
        return jedisTemplate.getString("user");
    }
}
