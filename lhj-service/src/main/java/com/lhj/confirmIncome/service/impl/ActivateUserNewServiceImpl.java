package com.lhj.confirmIncome.service.impl;

import com.lhj.confirmIncome.entity.ActivateUserNew;
import com.lhj.confirmIncome.mapper.ActivateUserNewMapper;
import com.lhj.confirmIncome.service.IActivateUserNewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Service
public class ActivateUserNewServiceImpl extends ServiceImpl<ActivateUserNewMapper, ActivateUserNew> implements IActivateUserNewService {

    @Override
    public List<String> getMonths(String appId) {
        return this.baseMapper.getMonths(appId);
    }
}
