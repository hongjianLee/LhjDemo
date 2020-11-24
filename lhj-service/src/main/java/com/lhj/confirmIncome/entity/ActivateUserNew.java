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
 * 
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ActivateUserNew对象", description="")
public class ActivateUserNew implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "统计id")
    private Long statisticalId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "消费金额")
    private BigDecimal consumePrice;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime consumeTime;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "商品名")
    private String goodsName;

    @ApiModelProperty(value = "创建者id")
    private Long createUser;

    @ApiModelProperty(value = "创建者名")
    private String createName;

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "有效期开始时间")
    private LocalDateTime expireStartTime;

    @ApiModelProperty(value = "有效期结束时间")
    private LocalDateTime expireEndTime;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "服务周期")
    private Integer serviceTime;

    @ApiModelProperty(value = "单笔确认收入")
    private BigDecimal spiltPrice;

    @ApiModelProperty(value = "年月")
    private LocalDate monthTime;

    @ApiModelProperty(value = "是否是当月确认收入")
    private Integer isCurrent;

    @ApiModelProperty(value = "累积确认收入")
    private BigDecimal allSpiltPrice;

    @ApiModelProperty(value = "未确认收入")
    private BigDecimal noPrice;

    @ApiModelProperty(value = "单次消耗点数")
    private Integer spiltPoint;

    @ApiModelProperty(value = "总点数")
    private Integer allPoint;


}
