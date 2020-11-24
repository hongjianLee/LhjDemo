package com.lhj.confirmIncome.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@ApiModel(value="StatisticalNew对象", description="")
public class StatisticalNew implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "月份")
    private LocalDate monthTime;

    @ApiModelProperty(value = "现金收入")
    private BigDecimal cashIncome;

    @ApiModelProperty(value = "新报学员")
    private Long newOpenId;

    @ApiModelProperty(value = "确认收入")
    private BigDecimal confirmIncome;

    @ApiModelProperty(value = "在读学员")
    private Long oldOpenId;

    @ApiModelProperty(value = "激活收入")
    private BigDecimal activeIncome;

    @ApiModelProperty(value = "激活学员")
    private Long activeOpenId;

    @ApiModelProperty(value = "创建者id")
    private Long createUser;

    @ApiModelProperty(value = "创建者名")
    private String createName;

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "当月确认收入")
    private BigDecimal currentConfirmIncome;

    @ApiModelProperty(value = "累积确认收入")
    private BigDecimal allConfirmIncome;

    @ApiModelProperty(value = "累积现金收入")
    private BigDecimal allCashIncome;

}
