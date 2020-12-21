package com.lhj.confirmIncome.service;

import com.lhj.confirmIncome.entity.ActivateUserNew;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
public interface IActivateUserNewService extends IService<ActivateUserNew> {

    List<String> getMonths(String appId);
}
