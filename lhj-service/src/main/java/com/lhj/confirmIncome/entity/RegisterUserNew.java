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
@ApiModel(value="RegisterUserNew对象", description="")
public class RegisterUserNew implements Serializable {

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

    @ApiModelProperty(value = "年月")
    private LocalDate monthTime;


}
