package com.lhj.confirmIncome.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 确认收入明细
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZdIncomeActual2对象", description="确认收入明细")
public class ZdIncomeActual2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer uid;

    @ApiModelProperty(value = "用户商品表id")
    private Integer ugid;

    private LocalDateTime activeTime;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    @ApiModelProperty(value = "计算得服务周期月数")
    private Integer periodMonth;

    @ApiModelProperty(value = "1:留学，2:考研")
    private Integer isOs;

    @ApiModelProperty(value = "是否工作签")
    private Integer isWk;


}
