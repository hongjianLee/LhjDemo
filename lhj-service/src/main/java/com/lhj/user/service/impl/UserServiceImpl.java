package com.lhj.user.service.impl;

import com.lhj.lhjcore.entity.User;
import com.lhj.user.mapper.UserMapper;
import com.lhj.lhjcore.IService.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-03
 */
@Service(version = "${dubbo.provider.version}", timeout = 100000)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
