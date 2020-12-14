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
 * 现金收入详情表
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CashIncomeInfo对象", description="现金收入详情表")
public class CashIncomeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "创建者id")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "确认收入统计表")
    private Long statisticsId;

    @ApiModelProperty(value = "年月")
    private LocalDate monthTime;

    @ApiModelProperty(value = "商品业务标记id")
    private Long businessTagId;

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "本单使用早元数量")
    private BigDecimal zaoyuan;

    @ApiModelProperty(value = "残值置换所使用的金额")
    private BigDecimal salvageValue;

    @ApiModelProperty(value = "实际现金收入")
    private BigDecimal cash;

    @ApiModelProperty(value = "订单总额")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "支付方式")
    private String payMethod;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "确认收入方式，例如 一次性确认或者分期确认等等")
    private Integer confirmIncomeMethod;

    @ApiModelProperty(value = "用户id")
    private Long openId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "服务周期")
    private Integer servicePeriod;

    @ApiModelProperty(value = "订单是否确认收入已完成 1 完成 2 未完成")
    private Integer isOrderCompleted;

    @ApiModelProperty(value = "首次确认收入")
    private BigDecimal firstConfirmIncome;

    @ApiModelProperty(value = "是否早元充值订单")
    private Integer isZaoyuanCharge;

    @ApiModelProperty(value = "是否进入confirm_income_details表中确认")
    private Integer isConfirming;

}
