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
 * 月确认收入详情
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConfirmIncomeDetails对象", description="月确认收入详情")
public class ConfirmIncomeDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "确认收入统计表id")
    private Long statisticsId;

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

    @ApiModelProperty(value = "订单号")
    private String orderCode;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "实际现金收入")
    private BigDecimal cash;

    @ApiModelProperty(value = "本单使用早元数量")
    private BigDecimal zaoyuan;

    @ApiModelProperty(value = "残值")
    private BigDecimal salvageValue;

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

    @ApiModelProperty(value = "是否是当月")
    private Integer isCurMonth;

    @ApiModelProperty(value = "当前期数")
    private Integer curPeriod;

    @ApiModelProperty(value = "当月确认收入")
    private BigDecimal curConfirmIncome;

    @ApiModelProperty(value = "剩余未确认收入")
    private BigDecimal leftNoConfirmIncome;

    @ApiModelProperty(value = "该订单总点数")
    private Integer points;

    @ApiModelProperty(value = "本次确认点数")
    private Integer curConfirmPoints;

    @ApiModelProperty(value = "已确认点数")
    private Integer confirmedPoints;

    @ApiModelProperty(value = "有效期开始时间")
    private LocalDateTime expireStartTime;

    @ApiModelProperty(value = "有效期结束时间")
    private LocalDateTime expireEndTime;

    @ApiModelProperty(value = "修改用户id")
    private Long updateUser;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "退款单id")
    private Long refundId;


}
