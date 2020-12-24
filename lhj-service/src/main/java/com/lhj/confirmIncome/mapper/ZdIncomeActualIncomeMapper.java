package com.lhj.confirmIncome.mapper;

import com.lhj.confirmIncome.entity.ZdIncomeActualIncome;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-19
 */
@Mapper
public interface ZdIncomeActualIncomeMapper extends BaseMapper<ZdIncomeActualIncome> {

    List<String> getMonths();
}
