package com.lhj.confirmIncome.service.impl;

import com.lhj.confirmIncome.entity.ZdIncomeActualSave;
import com.lhj.confirmIncome.mapper.ZdIncomeActualSaveMapper;
import com.lhj.confirmIncome.service.IZdIncomeActualSaveService;
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
public class ZdIncomeActualSaveServiceImpl extends ServiceImpl<ZdIncomeActualSaveMapper, ZdIncomeActualSave> implements IZdIncomeActualSaveService {


    @Override
    public List<String> getSaveMonth() {
        return this.baseMapper.getSaveMonth();
    }
}
