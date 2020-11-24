package com.lhj.confirmIncome.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="PayOrder对象", description="")
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "订单号")
    private String orderCode;

    @ApiModelProperty(value = "商品类型")
    private Integer goodsType;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    @ApiModelProperty(value = "是否测试")
    private Integer isTest;

    @ApiModelProperty(value = "支付类型")
    private Integer payType;

    @ApiModelProperty(value = "正常价格")
    private BigDecimal orginPrice;

    @ApiModelProperty(value = "优惠价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建人用户名")
    private String createUserName;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private Long updateUser;

    @ApiModelProperty(value = "修改人用户名")
    private String updateUserName;

    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "验证签名")
    private String sign;

    @ApiModelProperty(value = "订单状态")
    private Integer state;

    private String protocolUserMobile;

    private String protocolUserName;

    private String protocolId;

    private LocalDateTime expireDate;

    @TableField("payCenterOrderNo")
    private String payCenterOrderNo;

    private Long salePersonOpenId;

    private String salePersonName;

    @TableField("languageKind")
    private Integer languageKind;

    private String goodsName;

    private String orgCode;

    private String orgName;

    private Long referenceId;

    private LocalDateTime payTime;

    private String parentId;

    private BigDecimal paid;

    private Integer payOrderType;

    private String remark;

    @ApiModelProperty(value = "售卖平台ID")
    private String sellPlatform;

    @ApiModelProperty(value = "与crm相对应的type NL OC UP")
    private Integer crmType;

    @ApiModelProperty(value = "支付成功回调地址")
    private String notifyUrl;

    @ApiModelProperty(value = "访问TAG")
    private String accessTag;

    @ApiModelProperty(value = "访问IP")
    private String accessIp;

    private String thirdOrderId;

    private BigDecimal refundPrice;

    @ApiModelProperty(value = "是否是人工")
    @TableField("is_Artificial")
    private Integer isArtificial;

    @ApiModelProperty(value = "是否改价")
    @TableField("is_Price_Revision")
    private Integer isPriceRevision;

    @ApiModelProperty(value = "续费类型")
    private Integer followPayType;

    @ApiModelProperty(value = "商品封面")
    private String goodCoverUrl;

    @ApiModelProperty(value = "订单业绩")
    private BigDecimal orderAchievement;

    @ApiModelProperty(value = "商品卖价")
    private BigDecimal goodsSalePrice;

    @ApiModelProperty(value = "苹果内购收据")
    private String receipt;

    @ApiModelProperty(value = "苹果内购交易ID")
    private String transactionId;

    @ApiModelProperty(value = "早元抵扣")
    private BigDecimal zaoyuanDeduction;

    @ApiModelProperty(value = "早元扣减是否成功")
    private Integer zaoyuanDeductionState;

    @ApiModelProperty(value = "收入种类: 1.现金收入 2.混合收入 3.纯早元收入")
    private Integer receiptsType;

    @ApiModelProperty(value = "下单类型: 1:下单类型 2:代下单")
    private Integer generateOrderType;

    @ApiModelProperty(value = "是否为内部员工")
    private Integer isStaff;

    @ApiModelProperty(value = "业务类型ID")
    private Long businessTypeId;

    @ApiModelProperty(value = "是否为GAEA商品")
    private Integer isGaea;


}
