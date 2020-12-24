package com.lhj.confirmIncome.service.impl;

import com.lhj.confirmIncome.entity.ZdIncomeActualIncome;
import com.lhj.confirmIncome.mapper.ZdIncomeActualIncomeMapper;
import com.lhj.confirmIncome.service.IZdIncomeActualIncomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-19
 */
@Service
public class ZdIncomeActualIncomeServiceImpl extends ServiceImpl<ZdIncomeActualIncomeMapper, ZdIncomeActualIncome> implements IZdIncomeActualIncomeService {

    @Override
    public List<String> getMonths() {
        return this.baseMapper.getMonths();
    }
}
