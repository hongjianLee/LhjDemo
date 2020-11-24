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
 * 用户产品关联表
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StyUserGoods对象", description="用户产品关联表")
public class StyUserGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    @ApiModelProperty(value = "用户UID")
    private Long uid;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "1学习卡 2单品课, 3 交流, 4 考试, 5 综合")
    private Boolean typeMode;

    @ApiModelProperty(value = "业务类型 1:常规课 2:精品课 3:一对一 4:公开课程(已废弃) 5:公开课 6:旁听课 7:留学 8:合作课")
    private Integer businessType;

    @ApiModelProperty(value = "是否激活 1 激活 0 未激活")
    private Boolean isActivate;

    @ApiModelProperty(value = "激活方式 0未知 1自主激活 2系统激活 3后台激活 4自动激活")
    private Boolean activateType;

    @ApiModelProperty(value = "激活时间")
    private LocalDateTime activateTime;

    @ApiModelProperty(value = "有效期")
    private LocalDateTime expire;

    @ApiModelProperty(value = "确认收入有效期")
    private LocalDateTime incomeExpire;

    @ApiModelProperty(value = "可繁殖有效期")
    private LocalDateTime usableExpire;

    @ApiModelProperty(value = "不可繁殖有效期")
    private Integer giveExpire;

    @ApiModelProperty(value = "不限有效期标识 0:限制 1:不限")
    private Boolean unlimitExpire;

    @ApiModelProperty(value = "原价")
    private Integer price;

    @ApiModelProperty(value = "优惠价")
    private Integer discountPrice;

    @ApiModelProperty(value = "成交价")
    private Integer dealPrice;

    @ApiModelProperty(value = "购买基本信息")
    private String buyInfo;

    @ApiModelProperty(value = "允许激活时间")
    private LocalDateTime allowActivateTime;

    @ApiModelProperty(value = "从哪个商品升级来的")
    private Integer upgradeFrom;

    @ApiModelProperty(value = "要升级到的商品id")
    private Integer upgradeIntention;

    @ApiModelProperty(value = "是否保过：0不保过1保过")
    private Boolean guarantee;

    @ApiModelProperty(value = "0：不显示提示、1：显示提示")
    private Boolean showTip;

    @ApiModelProperty(value = "学习方案 1 自主选课 2 系统配课 4 人工配课")
    private Boolean learnProgram;

    @ApiModelProperty(value = "添加类型 1:新收 2:续费 3:测试 4:内部 5:合作")
    private Boolean addType;

    @ApiModelProperty(value = "添加类型(二级) 1:延期 2:升级")
    private Boolean addSubType;

    @ApiModelProperty(value = "删除类型 1:换商品 2:退费 3:内部员工 4:离职 5:测试")
    private Boolean delType;

    @ApiModelProperty(value = "停课类型 1:白条 2:爱海米 3:转卡嫌疑")
    private Boolean overdueType;

    @ApiModelProperty(value = "身份标识：1 普通学员 、2 vip 、3SVIP、 4终身卡、 5达人卡、6体验学员、7交流学员、8考试学员、9综合学员")
    private Boolean identity;

    @ApiModelProperty(value = "身份标识二级分类")
    private Integer subIdentity;

    private Boolean isDel;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime modifyTime;

    private Long modifyUser;


}
