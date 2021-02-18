package com.lhj.confirmIncome.service.impl;

import com.lhj.confirmIncome.entity.ConfirmIncomeDetails;
import com.lhj.confirmIncome.mapper.ConfirmIncomeDetailsMapper;
import com.lhj.confirmIncome.service.IConfirmIncomeDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 月确认收入详情 服务实现类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Service
public class ConfirmIncomeDetailsServiceImpl extends ServiceImpl<ConfirmIncomeDetailsMapper, ConfirmIncomeDetails> implements IConfirmIncomeDetailsService {

    @Override
    public List<String> getMonth() {
        return this.baseMapper.getMonthTime();
    }
}
