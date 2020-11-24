package com.lhj.confirmIncome.entity;

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
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="NetschoolPayOrder对象", description="")
public class NetschoolPayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    private String poSid;

    private String thirdOid;

    @ApiModelProperty(value = "子订单号")
    private String itemOid;

    private String poUser;

    @ApiModelProperty(value = "用户UID")
    private Long poUid;

    private String poStatus;

    @ApiModelProperty(value = "订单价格 支付价格+早元+优惠价格")
    private Integer orderPrice;

    @ApiModelProperty(value = "订单金额")
    private Integer poIn;

    @ApiModelProperty(value = "订单金额")
    private Integer poOut;

    @ApiModelProperty(value = "实际支付价格")
    private Integer payPrice;

    @ApiModelProperty(value = "jdpay折扣率价格")
    private Integer ratePrice;

    private Integer originalPrice;

    @ApiModelProperty(value = "生成订单时间")
    private Integer poSubmittime;

    private Integer poOktime;

    private String poReturnid;

    private String poExptend;

    @TableField("realName")
    private String realName;

    @ApiModelProperty(value = "学员手机号")
    private String mobile;

    @ApiModelProperty(value = "type为0时为班级ID，type为3时为活动ID,order_type=4时是user_renewal_id")
    private Integer classId;

    private String courseTitle;

    @ApiModelProperty(value = "订单类型 1充值，2购课, 3升级，4续费")
    private Integer orderType;

    @ApiModelProperty(value = "0:购课，1充值，2活动")
    private Integer type;

    @ApiModelProperty(value = "是否为活动订单")
    private Boolean isActive;

    @ApiModelProperty(value = "活动ID号")
    private Integer activeId;

    @ApiModelProperty(value = "支付时间")
    private Integer payTime;

    @ApiModelProperty(value = "支付方式: wx:微信支付, alipay:支付宝支付, netbank:网银支付, netpay:银联支付, fqlpay:分期乐, jdpay:京东白条, jingdongpay:京东支付, cardpay:卡密支付, lovehaimi:爱海米支付, jdzhuanying:京东专营店, tmall:天猫,xiaoetong:小鹅通, nadou: 纳豆代理")
    private String payType;

    @ApiModelProperty(value = "支付方式为汇款时所选择的银行")
    private String payBank;

    @ApiModelProperty(value = "支付返回地址")
    private String backurl;

    @ApiModelProperty(value = "支付同步回调地址")
    private String returnUrl;

    @ApiModelProperty(value = "优惠券ID")
    private Long cardId;

    @ApiModelProperty(value = "礼券ID")
    private Long couponId;

    @ApiModelProperty(value = "购物车ID")
    private Integer sId;

    @ApiModelProperty(value = "订单来源方式0:WEB,1:WAP,2APP，3卡券")
    private Integer posType;

    @ApiModelProperty(value = "订单来源为APP时的区分0:无,1:IOS,2:android")
    private Integer posInfo;

    @ApiModelProperty(value = "pos_type 为3时，记录卡券ID")
    private Integer posCouponId;

    @ApiModelProperty(value = "0:等待付款,1:付款成功,2已过期,3:已退款,4:订金单已付,5:余额单已付")
    private Integer status;

    @ApiModelProperty(value = "过期时间 0没有过期时间")
    private Integer expTime;

    @ApiModelProperty(value = "购买课程时所使用早元数量")
    private Integer useZy;

    @ApiModelProperty(value = "0未分期，1分期")
    private Integer isStages;

    @ApiModelProperty(value = "0未删，1删除")
    private Integer isDelete;

    @ApiModelProperty(value = "审核状态：-1未审核,0不通过,1通过")
    private Integer isVerify;

    @ApiModelProperty(value = "团队id")
    private Long deptId;

    @ApiModelProperty(value = "课程顾问UID")
    private Integer ccUid;

    @ApiModelProperty(value = "班主任UID")
    private Integer saUid;

    @ApiModelProperty(value = "续费顾问")
    private Integer rcUid;

    @ApiModelProperty(value = "留学顾问")
    private Integer oscUid;

    @ApiModelProperty(value = "收款类型 1学费,2补费,3续费,4转介绍,5转班费,6教材费,7定金,8退费,9续转退费,10邮费,11微店,12合作平台,13系统产品,14留学,15结业回归,16充值")
    private Integer collectionType;

    private String datetype;

    private String tag;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "0:自动,1:人工")
    private Integer isManual;

    @ApiModelProperty(value = "是否停课 - 0:不停课,1:停课")
    private Integer suspendClasses;

    @ApiModelProperty(value = "市场活动不进销售流水标识")
    private Integer marketSign;

    @ApiModelProperty(value = "0:否 1:是    人工修改价格 ")
    private Integer isChangePrice;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "是否为正式商品(仅给后台业绩使用), 1 是, 2 否")
    private Boolean isOfficialGoods;

    @ApiModelProperty(value = "人工配课记录用户选择的阶段ID")
    private Integer scheduleId;

    @ApiModelProperty(value = "是否是测试数据 0否 1是")
    private Integer isTestData;

    @ApiModelProperty(value = "从哪个商品升级而来")
    private Integer originGoodsId;

    @ApiModelProperty(value = "退费关联订单号")
    private String relatePosid;

    @ApiModelProperty(value = "下单ip")
    private String ip;

    @ApiModelProperty(value = "代下单id")
    private Integer agentId;

    @ApiModelProperty(value = "订单类型 1:’正常’,2:’定金订单’,3:’余额订单’,4:’母单’")
    private Integer orderBranch;

    @ApiModelProperty(value = "‘定金订单’或’余额订单’支付价格")
    private Integer branchPrice;

    @ApiModelProperty(value = "母单订单号")
    private String motherOid;


}
