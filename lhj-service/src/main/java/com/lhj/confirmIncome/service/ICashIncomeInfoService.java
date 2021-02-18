package com.lhj.confirmIncome.service;

import com.lhj.confirmIncome.entity.CashIncomeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 现金收入详情表 服务类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
public interface ICashIncomeInfoService extends IService<CashIncomeInfo> {

    List<String> getMonthTime();
}
