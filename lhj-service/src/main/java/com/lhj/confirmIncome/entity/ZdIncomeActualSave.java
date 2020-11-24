package com.lhj.confirmIncome.entity;

import java.math.BigDecimal;
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
@ApiModel(value="ZdIncomeActualSave对象", description="")
public class ZdIncomeActualSave implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String businessType;

    private Integer ugid;

    private String actualDate;

    private String actualYear;

    private String actualMonth;

    @ApiModelProperty(value = "当月确认")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "节点收入")
    private BigDecimal monthAmount;

    @ApiModelProperty(value = "总收入")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "节点期数")
    private Integer periodMonth;

    @ApiModelProperty(value = "剩余未确认")
    private BigDecimal actualLeft;


}
