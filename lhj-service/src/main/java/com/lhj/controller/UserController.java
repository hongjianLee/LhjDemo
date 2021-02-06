package com.lhj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhj.lhjcore.IService.IUserService;
import com.lhj.lhjcore.contants.NumberConstants;
import com.lhj.lhjcore.entity.User;
import com.lhj.utils.redis.JedisTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

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

    @Autowired
    private RedissonClient redissonClient;

    private Lock lock = new ReentrantLock(true);

    private Integer num = NumberConstants.INT_100;

    @GetMapping("get/{id}")
    public String get(@PathVariable Long id) {

        Map<String, Object> map = new HashMap<>(NumberConstants.INT_2);
        map.put("id", id);
        return userService.listByMap(map).toString();
    }

    @GetMapping("redis/{id}")
    public String redis(@PathVariable Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userService.getOne(wrapper);
        jedisTemplate.setString("user", user.getName(), NumberConstants.INT_1000);
        return jedisTemplate.getString("user");
    }


//   redisson分布式锁  https://www.bookstack.cn/read/redisson-doc-cn/distributed_locks.md
    @GetMapping(value = "/redisson/{key}")
    public String redissonTest(@PathVariable("key") String lockKey) {
//        公平锁
        RLock lock = redissonClient.getFairLock(lockKey);
        try {
            lock.lock();
            System.out.println("执行任务中--" + num);
            Thread.sleep(NumberConstants.LONG_5000);
            if (num.equals(NumberConstants.INT_0)) {
                return "失败";
            }
            num--;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "成功";
    }

    @GetMapping(value = "/sayWorld")
    public String world() {
        try {
            lock.lock();
            Thread.sleep(NumberConstants.LONG_5000);
            System.out.println("world");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "world";
    }

}
