package com.lhj.confirmIncome.mapper;

import com.lhj.confirmIncome.entity.ConfirmIncomeDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 月确认收入详情 Mapper 接口
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Mapper
public interface ConfirmIncomeDetailsMapper extends BaseMapper<ConfirmIncomeDetails> {

    List<String> getMonthTime();
}
