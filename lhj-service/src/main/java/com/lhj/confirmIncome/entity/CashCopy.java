package com.lhj.confirmIncome.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 导入cash_income_details预处理表
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CashCopy对象", description="导入cash_income_details预处理表")
public class CashCopy implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String createUser;

    private LocalDateTime createTime;

    private String appId;

    private String businessTagId;

    private String orderCode;

    private String goodsName;

    private String goodsId;

    private Integer zaoyuan;

    private Integer salvageValue;

    private Integer cash;

    private Integer orderPrice;

    private String payMethod;

    private LocalDateTime payTime;

    @TableField("confirmIn_income_method")
    private Integer confirminIncomeMethod;

    private String openId;

    private String userName;


}
