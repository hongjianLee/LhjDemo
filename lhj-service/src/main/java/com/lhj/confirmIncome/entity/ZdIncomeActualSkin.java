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
@ApiModel(value="ZdIncomeActualSkin对象", description="")
public class ZdIncomeActualSkin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String businessType;

    private String actualDate;

    @ApiModelProperty(value = "现金总额")
    private BigDecimal curAmount;

    @ApiModelProperty(value = "新报学员")
    private Integer newStudent;

    @ApiModelProperty(value = "激活收入")
    private BigDecimal activeAmount;

    @ApiModelProperty(value = "激活学员")
    private Integer activeStudent;

    @ApiModelProperty(value = "未激活收入")
    private BigDecimal unActiveAmount;

    @ApiModelProperty(value = "未激活学员")
    private Integer unActiveStudent;

    @ApiModelProperty(value = "确认收入")
    private BigDecimal actualCharge;

    @ApiModelProperty(value = "剩余未确认")
    private BigDecimal unActualCharge;

    @ApiModelProperty(value = "在读学员")
    private Integer curStudent;


}
