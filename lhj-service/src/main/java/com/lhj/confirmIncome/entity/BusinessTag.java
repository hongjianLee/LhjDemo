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
 * 商品属性的业务标记
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BusinessTag对象", description="商品属性的业务标记")
public class BusinessTag implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "业务标记名称")
    private String tagName;

    @ApiModelProperty(value = "是否启用：1、启用  2、停用")
    private Integer state;

    @ApiModelProperty(value = "创建者id")
    private Long createUser;

    @ApiModelProperty(value = "创建者名字")
    private String createName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
