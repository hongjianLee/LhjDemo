package com.lhj.confirmIncome.service;

import com.lhj.confirmIncome.entity.ConfirmIncomeDetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 月确认收入详情 服务类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
public interface IConfirmIncomeDetailsService extends IService<ConfirmIncomeDetails> {

    List<String> getMonth();
}
