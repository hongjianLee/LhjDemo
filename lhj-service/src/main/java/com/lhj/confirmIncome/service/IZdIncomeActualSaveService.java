package com.lhj.confirmIncome.service;

import com.lhj.confirmIncome.entity.ZdIncomeActualSave;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-19
 */
public interface IZdIncomeActualSaveService extends IService<ZdIncomeActualSave> {

    List<String> getSaveMonth();
}
