package com.lhj.confirmIncome.mapper;

import com.lhj.confirmIncome.entity.ActivateUserNew;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Mapper
public interface ActivateUserNewMapper extends BaseMapper<ActivateUserNew> {

    List<String> getMonths(@Param("appId") String appId);
}
