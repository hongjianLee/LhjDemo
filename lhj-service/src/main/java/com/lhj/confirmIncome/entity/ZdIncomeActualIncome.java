package com.lhj.confirmIncome.entity;

import java.math.BigDecimal;
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
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZdIncomeActualIncome对象", description="")
public class ZdIncomeActualIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String dataDate;

    private String businessType;

    @ApiModelProperty(value = "订单号")
    private String poSid;

    private String courseTitle;

    @ApiModelProperty(value = "后台分类")
    private String backgroundCat;

    @ApiModelProperty(value = "实际支付价格")
    private Integer payPrice;

    private String payType;

    private LocalDateTime adddate;

    private String poUser;

    private Integer poUid;

    @ApiModelProperty(value = "是否当月加课")
    private Integer isJoin;

    @ApiModelProperty(value = "是否当月激活")
    private Integer isActive;

    private LocalDateTime activeTime;

    private Integer periodMonth;

    @ApiModelProperty(value = "当月确认")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "节点收入")
    private BigDecimal monthAmount;


}
