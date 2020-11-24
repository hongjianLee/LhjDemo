package com.lhj.confirmIncome.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 确认收入统计总表
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConfirmIncomeStatistics对象", description="确认收入统计总表")
public class ConfirmIncomeStatistics implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "创建者id")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "业务标记id")
    private Long businessTagId;

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "年月")
    private LocalDate monthTime;

    @ApiModelProperty(value = "当前月购课订单数量")
    private Integer orderSum;

    @ApiModelProperty(value = "本月购课早元数")
    private BigDecimal zaoyuanIncome;

    @ApiModelProperty(value = "本月购课总残值")
    private BigDecimal salvageValueIncome;

    @ApiModelProperty(value = "本月购课总现金")
    private BigDecimal cashIncome;

    @ApiModelProperty(value = "本月新订单确认收入")
    private BigDecimal newOrderConfirmIncome;

    @ApiModelProperty(value = "往期订单确认收入")
    private BigDecimal oldOrderConfirmIncome;

    @ApiModelProperty(value = "早元充值收入")
    private BigDecimal zaoyuanChargeIncome;

    @ApiModelProperty(value = "期初早元余额")
    private BigDecimal beginTermLeftZaoyuan;

    @ApiModelProperty(value = "期初现金余额")
    private BigDecimal beginTermLeftCash;

    @ApiModelProperty(value = "期末早元余额")
    private BigDecimal endTermLeftZaoyuan;

    @ApiModelProperty(value = "期末现金余额")
    private BigDecimal endTermLeftCash;

    @ApiModelProperty(value = "本月到期订单尾款总金额")
    private BigDecimal orderFinalPaymentSum;

    @ApiModelProperty(value = "本月新订单未确认收入")
    private BigDecimal newOrderNoConfirmIncome;

    @ApiModelProperty(value = "往期订单未确认收入")
    private BigDecimal oldOrderNoConfirmIncome;

    @ApiModelProperty(value = "往期未确认收入")
    private BigDecimal pastNoConfirmIncome;
}
