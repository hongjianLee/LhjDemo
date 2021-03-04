package com.lhj.confirmIncome.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhj.confirmIncome.entity.ActivateUserNew;
import com.lhj.confirmIncome.entity.CashIncomeInfo;
import com.lhj.confirmIncome.entity.ConfirmIncomeDetails;
import com.lhj.confirmIncome.entity.ConfirmIncomeStatistics;
import com.lhj.confirmIncome.entity.NetschoolPayOrder;
import com.lhj.confirmIncome.entity.PayOrder;
import com.lhj.confirmIncome.entity.RegisterUserNew;
import com.lhj.confirmIncome.entity.StatisticalNew;
import com.lhj.confirmIncome.entity.StyUserGoods;
import com.lhj.confirmIncome.entity.ZdIncomeActualIncome;
import com.lhj.confirmIncome.entity.ZdIncomeActualSave;
import com.lhj.confirmIncome.entity.ZdIncomeActualSkin;
import com.lhj.confirmIncome.service.IActivateUserNewService;
import com.lhj.confirmIncome.service.ICashIncomeInfoService;
import com.lhj.confirmIncome.service.IConfirmIncomeDetailsService;
import com.lhj.confirmIncome.service.IConfirmIncomeStatisticsService;
import com.lhj.confirmIncome.service.INetschoolPayOrderService;
import com.lhj.confirmIncome.service.IPayOrderService;
import com.lhj.confirmIncome.service.IRegisterUserNewService;
import com.lhj.confirmIncome.service.IStatisticalNewService;
import com.lhj.confirmIncome.service.IStyUserGoodsService;
import com.lhj.confirmIncome.service.IZdIncomeActualIncomeService;
import com.lhj.confirmIncome.service.IZdIncomeActualSaveService;
import com.lhj.confirmIncome.service.IZdIncomeActualSkinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-16
 */
@Slf4j
@Api(description = "确认收入数据处理")
@RestController
@RequestMapping("/confirmIncome/register-user-new")
public class RegisterUserNewController {

    private List<String> appIds = new ArrayList<>();

    private List<String> busienssTypes = new ArrayList<>();

//    appIds
    private RegisterUserNewController() {
        appIds.add("06fedeb667084d0f9b6666e8baccc9e1");
        appIds.add("33447a0a428548b4bccf9c9fb39565c4");
        appIds.add("559d6de1e10e4470b21715ff7041412e");
        appIds.add("6900443d62c54f5090604f0468a5f20e");
        appIds.add("72d6c41307e14c478d836072a62db662");
        appIds.add("78e458833cb84331840c14b8a05a9153");
        appIds.add("887b86de893d476fb83d6d0f9a7fa834");
        appIds.add("9058f61b5e814c2ebaa4e062df162235");
        appIds.add("98f4fbb644294aba83b59a54b8c3af9d");
        appIds.add("d174503a1da9453aa23e01a31c911e73");
        appIds.add("f240292f76bf4bb1a2336f1fa51421c9");
        busienssTypes.add("jp");
        busienssTypes.add("os");
        busienssTypes.add("ky");
        busienssTypes.add("jz");
    }

    @Autowired
    private IRegisterUserNewService registerUserNewService;

    @Autowired
    private IActivateUserNewService activateUserNewService;

    @Autowired
    private IStatisticalNewService statisticalNewService;

    @Autowired
    private IPayOrderService payOrderService;

    @Autowired
    private ICashIncomeInfoService cashIncomeInfoService;

    @Autowired
    private IConfirmIncomeDetailsService confirmIncomeDetailsService;

    @Autowired
    private IConfirmIncomeStatisticsService confirmIncomeStatisticsService;

    @Autowired
    private IZdIncomeActualSkinService zdIncomeActualSkinService;

    @Autowired
    private IZdIncomeActualSaveService zdIncomeActualSaveService;

    @Autowired
    private IStyUserGoodsService styUserGoodsService;

    @Autowired
    private IZdIncomeActualIncomeService zdIncomeActualIncomeService;

    @Autowired
    private INetschoolPayOrderService netschoolPayOrderService;
    /**
     * 处理小语种数据
     *
     * @return String
     */
    @GetMapping("dealWithMinilangData")
    public String dealWithMinilangData() {
        appIds.stream().forEach(appId -> {
            this.dealWithData(appId);
            this.confirmIncomeData(appId);
            this.statisticsData(appId);
        });
        return "success";
    }



    /**
     * 处理现金收入表数据
     *
     * @return String
     */
    @ApiOperation(value = "第一步小语种现金收入处理1", notes = "第一步小语种现金收入处理2")
    @GetMapping("dealWithCashData")
    public String dealWith() {
        appIds.stream().forEach(appId -> {
            this.dealWithData(appId);
        });
        return "success";
    }

    /**
     * 处理确认收入表数据
     *
     * @return String
     */
    @ApiOperation(value = "第二步小语种确认收入处理1", notes = "第二步小语种确认收入处理2")
    @GetMapping("dealWithConfirmData")
    public String dealWithConfirmData() {
        appIds.stream().forEach(appId -> {
            this.confirmIncomeData(appId);
        });
        return "success";
    }

    /**
     * 处理统计表数据
     *
     * @return String
     */
    @ApiOperation(value = "第三步小语种统计表数据处理1", notes = "第二步小语种统计表数据处理2")
    @GetMapping("dealWithStatistics")
    public String dealWithStatistics() {
        appIds.stream().forEach(appId -> {
            this.statisticsData(appId);
        });
        return "success";
    }

    /**
     * 日语现金收入处理
     *
     * @return String
     */
    @ApiOperation(value = "第五步日语现金收入数据处理1",notes = "第五步日语现金收入数据处理2")
    @GetMapping("dealWithJapanCashData")
    public String dealWithJapanCashData() {
//        现金收入月份
        List<String> months = zdIncomeActualIncomeService.getMonths();
//        分月份处理
        months.stream()
//                .filter(o -> DateUtil.parseDate(o + "-01").isAfter(DateUtil.parseDate("2020-09-01")) )
                .forEach(item -> {
                    log.error(item);
                    Date date = DateUtil.parseDate(item + "-01");
                    LocalDate monthTime = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, 2);
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("data_date", item);
                    List<ZdIncomeActualIncome> list = zdIncomeActualIncomeService.list(queryWrapper);
                    List<CashIncomeInfo> cashIncomeInfos = new ArrayList<>();
                    list.stream().forEach(income -> {
                        log.error(income.getId().toString());
                        CashIncomeInfo cash = new CashIncomeInfo();
                        cash.setId(income.getId());
                        cash.setCreateUser(Long.valueOf(income.getPoUid()));
                        cash.setCreateTime(LocalDateTime.now());
                        cash.setAppId(this.getAppIdByJpTag(income.getBusinessType()));
                        cash.setStatisticsId(this.getStatisticsId(item + "-01", cash.getAppId()));
                        cash.setMonthTime(monthTime);
                        cash.setBusinessTagId(
                                Long.valueOf(this.getBusinessTagIdByJpTag(income.getBusinessType()))
                        );
                        cash.setOrderCode(income.getPoSid().trim());
                        cash.setGoodsName(income.getCourseTitle());
//                cash.setGoodsId();
                        cash.setZaoyuan(BigDecimal.ZERO);
                        cash.setSalvageValue(BigDecimal.ZERO);
                        cash.setCash(BigDecimal.valueOf(income.getPayPrice()));
                        cash.setOrderPrice(cash.getCash());
                        cash.setPayMethod(this.getPayMethod(income.getPayType()));
                        cash.setPayTime(income.getAdddate());
                        cash.setConfirmIncomeMethod(1);
                        cash.setOpenId(Long.valueOf(income.getPoUid()));
                        cash.setUserName(income.getPoUser());
                        cash.setServicePeriod(income.getPeriodMonth());
                        cash.setIsOrderCompleted(2);
                        cash.setFirstConfirmIncome(BigDecimal.ZERO);
                        cash.setIsZaoyuanCharge(
                                "早元充值".equals(cash.getGoodsName())
                                        ? 1 : 2
                        );
                        cash.setIsConfirming(1);
                        cashIncomeInfos.add(cash);
                    });
                    boolean flag = cashIncomeInfoService.saveBatch(cashIncomeInfos);
                    log.error(String.valueOf(flag));
                });
        return "success";
    }

    @ApiOperation(value = "第六步日语确认收入数据处理1", notes = "第六步日语确认收入数据处理2")
    @GetMapping("dealWithJapanConfirmIncome")
    public String dealWithJapanConfirmIncome() {
        List<String> months = zdIncomeActualSaveService.getSaveMonth();
        months.stream()
//                .filter(item -> DateUtil.parseDate(item + "-01").after(DateUtil.parseDate("2021-01-01")))
                .forEach(o -> {
                    QueryWrapper<ZdIncomeActualSave> queryWrapper = new QueryWrapper<>();
                    queryWrapper.ne("actual_amount", 0);
                    queryWrapper.ne("period_month", 0);
                    queryWrapper.eq("actual_date", o);
                    List<ZdIncomeActualSave> zdIncomeActualSaveList = zdIncomeActualSaveService.list(queryWrapper);
                    Date date = DateUtil.parseDate(o + "-01");
                    LocalDate monthTime = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, 2);
                    this.dealConfirm(zdIncomeActualSaveList, monthTime);
                });
        return "success";
    }

    /**
     * dealWithJapanStatistics
     *
     * @return String
     */
    @ApiOperation(value = "第四步日语统计表数据处理1",notes = "第四步日语统计表数据处理2")
    @GetMapping("dealWithJapanStatistics")
    public String dealWithJapanStatistics() {
        List<ZdIncomeActualSkin> zdIncomeActualSkins = zdIncomeActualSkinService.list();
        Vector<ConfirmIncomeStatistics> confirmIncomeStatisticsVector = new Vector<>();
        zdIncomeActualSkins.stream().forEach(item -> {
            log.error(String.valueOf(item.getId()));
            ConfirmIncomeStatistics param = new ConfirmIncomeStatistics();
            param.setId(item.getId());
            param.setCreateUser(818261895487888888L);
            param.setCreateTime(LocalDateTime.now());
            param.setAppId(this.getAppIdByJpTag(item.getBusinessType()));
            param.setBusinessTagId(
                    Long.valueOf(
                            this.getBusinessTagId(
                                    param.getAppId()
                            )
                    )
            );

            Date date = DateUtil.parseDate(item.getActualDate() + "-01");
            LocalDateTime localDateTime = LocalDateTimeUtil.of(date);
            param.setMonthTime(
                    LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), 2)
            );
            param.setOrderSum(item.getNewStudent());
            param.setZaoyuanIncome(BigDecimal.ZERO);
            param.setSalvageValueIncome(BigDecimal.ZERO);
            param.setCashIncome(item.getCurAmount());
            param.setNewOrderConfirmIncome(BigDecimal.ZERO);
            param.setOldOrderConfirmIncome(item.getActualCharge());
            param.setNewOrderNoConfirmIncome(BigDecimal.ZERO);
            param.setOldOrderNoConfirmIncome(item.getUnActualCharge());
            confirmIncomeStatisticsVector.add(param);
        });
//        List<ConfirmIncomeStatistics> jpList = this.dealStatistics(confirmIncomeStatisticsVector,"8275314362b14956867bf00673a0af33");
//        List<ConfirmIncomeStatistics> lxList = this.dealStatistics(confirmIncomeStatisticsVector, "80c5c10b75eb4e61a7bceb02ba649698");
//        List<ConfirmIncomeStatistics> kyList = this.dealStatistics(confirmIncomeStatisticsVector, "06c19ba4da1740fa9169f792b04d92e6");
//        List<ConfirmIncomeStatistics> jzList = this.dealStatistics(confirmIncomeStatisticsVector, "14e8933254dd461f92b18ae0d9294317");
//        confirmIncomeStatisticsVector.clear();
//        confirmIncomeStatisticsVector.addAll(jpList);
//        confirmIncomeStatisticsVector.addAll(lxList);
//        confirmIncomeStatisticsVector.addAll(kyList);
//        confirmIncomeStatisticsVector.addAll(jzList);
        boolean flag = confirmIncomeStatisticsService.saveBatch(confirmIncomeStatisticsVector);
        log.error(String.valueOf(flag));
        return "success";
    }


    private void dealConfirm(List<ZdIncomeActualSave> zdIncomeActualSaves, LocalDate monthTime) {
        Vector<ConfirmIncomeDetails> confirmIncomeDetails = new Vector<>();
        zdIncomeActualSaves.stream().forEach(zdIncomeActualSave -> {
            log.error(zdIncomeActualSave.getId().toString());
            ConfirmIncomeDetails con = new ConfirmIncomeDetails();
            QueryWrapper qw = new QueryWrapper();
            qw.eq("id", zdIncomeActualSave.getUgid());
            StyUserGoods styUserGoods = styUserGoodsService.getOne(qw);
            if (Objects.nonNull(styUserGoods)) {
                con.setCreateUser(styUserGoods.getUid());
                con.setGoodsId(Long.valueOf(styUserGoods.getGoodsId()));
                con.setGoodsName(styUserGoods.getGoodsName());
                con.setOpenId(styUserGoods.getUid());
                con.setExpireStartTime(styUserGoods.getActivateTime());
                con.setExpireEndTime(
                        con.getExpireStartTime().plusMonths(zdIncomeActualSave.getPeriodMonth())
                );
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("po_uid", styUserGoods.getUid());
                queryWrapper.eq("goods_id", styUserGoods.getGoodsId());
                queryWrapper.eq("po_status", "交易成功");
                queryWrapper.ne("pay_price", 0);
                Vector<NetschoolPayOrder> np
                        = new Vector<>(netschoolPayOrderService.list(queryWrapper));
                if (CollectionUtil.isNotEmpty(np)) {
                    con.setOrderCode(
                            np.get(0).getPoSid().trim()
                    );
                    con.setPayMethod(np.get(0).getPayType());
                    con.setPayTime(
                            LocalDateTime.ofEpochSecond(np.get(0).getPayTime(), 0, ZoneOffset.ofHours(8))
                    );
                    con.setUserName(np.get(0).getPoUser());
                } else {
                    con.setOrderCode(zdIncomeActualSave.getUgid().toString());
                    con.setPayMethod("");
                }
            } else {
                con.setCreateUser(0L);
                con.setOpenId(0L);
                con.setOrderCode(zdIncomeActualSave.getUgid().toString());
                con.setPayMethod("");
            }
            con.setId(zdIncomeActualSave.getId());
            con.setCreateTime(LocalDateTime.now());
            con.setBusinessTagId(Long.valueOf(this.getBusinessTagIdByJpTag(zdIncomeActualSave.getBusinessType())));
            con.setAppId(this.getAppIdByJpTag(zdIncomeActualSave.getBusinessType()));
            con.setMonthTime(monthTime);
            con.setCash(zdIncomeActualSave.getTotalAmount());
            con.setZaoyuan(BigDecimal.ZERO);
            con.setSalvageValue(BigDecimal.ZERO);
            con.setOrderPrice(con.getCash());
            con.setConfirmIncomeMethod(1);
            con.setServicePeriod(zdIncomeActualSave.getPeriodMonth());
            con.setStatisticsId(
                    this.getStatisticsId(this.localDateToString(con.getMonthTime()) + "-01", con.getAppId())
            );
            QueryWrapper qu = new QueryWrapper();
            qu.eq("ugid", zdIncomeActualSave.getUgid());
            qu.lt("actual_date", zdIncomeActualSave.getActualDate());
            con.setCurPeriod(zdIncomeActualSaveService.count(qu) + 1);
            con.setIsCurMonth(
                    con.getCurPeriod() == 1
                            ? 1 : 2
            );
            con.setCurConfirmIncome(zdIncomeActualSave.getActualAmount());
            con.setLeftNoConfirmIncome(zdIncomeActualSave.getActualLeft());
            confirmIncomeDetails.add(con);
        });
        boolean flag = confirmIncomeDetailsService.saveBatch(confirmIncomeDetails);
        log.error(String.valueOf(flag));
    }

    private List<ConfirmIncomeStatistics> dealStatistics(List<ConfirmIncomeStatistics> list, String appId) {
        List<ConfirmIncomeStatistics> confirmIncomeStatistics
                = list.stream()
                .filter(o -> appId.equals(o.getAppId()))
                .sorted(Comparator.comparing(ConfirmIncomeStatistics::getMonthTime))
                .collect(Collectors.toList());
        for (int i = 0; i < confirmIncomeStatistics.size(); i++) {
            if (i == 0) {
                continue;
            }
            confirmIncomeStatistics.get(i)
                    .setPastNoConfirmIncome(
                            confirmIncomeStatistics.get(i - 1).getNewOrderNoConfirmIncome().add(confirmIncomeStatistics.get(i - 1).getOldOrderNoConfirmIncome())
                    );
        }
        return list;
    }


    // 小语种现金收入
    private void dealWithData(String appId) {
        final String businessTagId = this.getBusinessTagId(appId);
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", appId);
        List<RegisterUserNew> registerUserNewList = registerUserNewService.listByMap(map);
        List<CashIncomeInfo> cashIncomeInfoList = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(registerUserNewList)) {
            registerUserNewList.stream()
                    .filter(a -> !"德语组一".equals(a.getOrderId())
                            && !"韩语组一".equals(a.getOrderId()))
                    .forEach(item -> {
                        CashIncomeInfo cashIncomeInfo = new CashIncomeInfo();
                        QueryWrapper queryWrapper = new QueryWrapper<PayOrder>();
                        queryWrapper.eq("order_code", item.getOrderId());
                        PayOrder payOrder = payOrderService.getOne(queryWrapper);
                        log.error(item.getOrderId().toString());
                        Map<String, Object> actMap = new HashMap<>();
                        if (payOrder.getPayOrderType() == 2 || payOrder.getPayOrderType() == 3) {
                            actMap.put("order_id", payOrder.getParentId());
                            cashIncomeInfo.setOrderCode(payOrder.getParentId());
                        } else {
                            actMap.put("order_id", payOrder.getOrderCode());
                            cashIncomeInfo.setOrderCode(item.getOrderId());
                        }
                        List<ActivateUserNew> activateUserNews
                                = activateUserNewService.listByMap(actMap);

                        cashIncomeInfo.setId(item.getId());
                        cashIncomeInfo.setCreateUser(item.getCreateUser());
                        cashIncomeInfo.setCreateTime(LocalDateTime.now());
                        cashIncomeInfo.setStatisticsId(item.getStatisticalId());
                        cashIncomeInfo.setMonthTime(LocalDate.of(item.getConsumeTime().getYear(), item.getConsumeTime().getMonthValue(), 2));
                        if (StringUtils.isNotEmpty(businessTagId)) {
                            cashIncomeInfo.setBusinessTagId(Long.valueOf(businessTagId));
                        }
                        cashIncomeInfo.setAppId(appId);
                        cashIncomeInfo.setGoodsName(item.getGoodsName());
                        cashIncomeInfo.setGoodsId(item.getGoodsId());
                        cashIncomeInfo.setZaoyuan(BigDecimal.ZERO);
                        cashIncomeInfo.setSalvageValue(BigDecimal.ZERO);
                        cashIncomeInfo.setCash(item.getConsumePrice());
                        cashIncomeInfo.setOrderPrice(item.getConsumePrice());
                        cashIncomeInfo.setPayMethod(this.getValueByPayType(payOrder.getPayType()));
                        cashIncomeInfo.setPayTime(item.getConsumeTime());
                        cashIncomeInfo.setConfirmIncomeMethod(1);
                        cashIncomeInfo.setOpenId(item.getCreateUser());
                        cashIncomeInfo.setUserName(item.getCreateName());
//                        微课，青柠五十音现金收入即确认收入
                        if (item.getAppId().equals("f240292f76bf4bb1a2336f1fa51421c9")
                                || item.getAppId().equals("72d6c41307e14c478d836072a62db662")) {
                            cashIncomeInfo.setConfirmIncomeMethod(2);
                            cashIncomeInfo.setIsConfirming(2);
                        }
                        if (item.getAppId().equals("887b86de893d476fb83d6d0f9a7fa834")) {
                            cashIncomeInfo.setConfirmIncomeMethod(4);
                        }
                        if (CollectionUtil.isNotEmpty(activateUserNews)) {
                            cashIncomeInfo.setIsConfirming(1);
                            cashIncomeInfo.setServicePeriod(activateUserNews.get(0).getServiceTime());
                            cashIncomeInfo.setFirstConfirmIncome(
                                    activateUserNews.stream()
                                            .filter(o -> o.getMonthTime() != null)
                                            .min(Comparator.comparing(ActivateUserNew::getMonthTime))
                                            .get().getSpiltPrice()
                            );
                        } else {
                            cashIncomeInfo.setServicePeriod(1);
                            cashIncomeInfo.setFirstConfirmIncome(BigDecimal.ZERO);
                        }
                        cashIncomeInfo.setIsZaoyuanCharge(2);
                        cashIncomeInfoList.add(cashIncomeInfo);
                    });

        }
        boolean flag = cashIncomeInfoService.saveBatch(cashIncomeInfoList);
        log.error(String.valueOf(flag));
    }

    // 小语种确认收入
    private void confirmIncomeData(String appId) {
        List<String> monthTimes = activateUserNewService.getMonths(appId);
        monthTimes.stream()
                .filter(o -> StringUtils.isNotEmpty(o))
                .forEach(monthTime -> {
                    final String businessTagId = this.getBusinessTagId(appId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("app_id", appId);
                    map.put("month_time", monthTime);
                    List<ActivateUserNew> activateUserNews = activateUserNewService.listByMap(map);
                    Vector<ConfirmIncomeDetails> confirmIncomeDetails = new Vector<>();
                    if (CollectionUtil.isNotEmpty(activateUserNews)) {
                        activateUserNews.parallelStream().forEach(item -> {
                            log.error(item.getOrderId());
                            QueryWrapper queryWrapper = new QueryWrapper<PayOrder>();
                            queryWrapper.eq("order_code", item.getOrderId());
                            PayOrder payOrder = payOrderService.getOne(queryWrapper);
                            ConcurrentHashMap<String, Object> actMap = new ConcurrentHashMap<>();
                            actMap.put("order_id", payOrder.getOrderCode());
                            Vector<ActivateUserNew> activateUserNewVector
                                    = new Vector<ActivateUserNew>(activateUserNewService.listByMap(actMap));
                            ConfirmIncomeDetails param = new ConfirmIncomeDetails();
                            param.setId(item.getId());
                            param.setStatisticsId(item.getStatisticalId());
                            param.setCreateUser(item.getCreateUser());
                            param.setCreateTime(LocalDateTime.now());
                            if (StringUtils.isNotEmpty(businessTagId)) {
                                param.setBusinessTagId(Long.valueOf(businessTagId));
                            }
                            param.setAppId(appId);
                            param.setMonthTime(
                                    LocalDate.of(item.getMonthTime().getYear(), item.getMonthTime().getMonthValue(), 2)
                            );
                            param.setOrderCode(item.getOrderId());
                            param.setGoodsId(item.getGoodsId());
                            param.setGoodsName(item.getGoodsName());
                            param.setCash(item.getConsumePrice());
                            param.setOrderPrice(item.getConsumePrice());
                            param.setPayMethod(this.getValueByPayType(payOrder.getPayType()));
                            param.setPayTime(item.getConsumeTime());
                            param.setOpenId(item.getCreateUser());
                            param.setUserName(item.getCreateName());
                            param.setServicePeriod(item.getServiceTime());
                            if (item.getIsCurrent() != null && 1 == item.getIsCurrent()) {
                                param.setIsCurMonth(item.getIsCurrent());
                            }
                            param.setCurPeriod(
                                    (int) activateUserNewVector.stream()
                                            .filter(o -> o.getMonthTime().isBefore(item.getMonthTime())
                                                    || o.getMonthTime().equals(item.getMonthTime())).count()
                            );
                            param.setCurConfirmIncome(item.getSpiltPrice());
                            param.setLeftNoConfirmIncome(item.getNoPrice());
                            param.setPoints(item.getAllPoint());
                            param.setCurConfirmPoints(item.getSpiltPoint());
                            param.setConfirmedPoints(0);
                            if ("887b86de893d476fb83d6d0f9a7fa834".equals(appId)) {
                                param.setConfirmIncomeMethod(4);
                                activateUserNewVector.stream()
                                        .filter(o -> o.getConsumeTime().isBefore(item.getConsumeTime())
                                                && o.getSpiltPoint() != null).forEach(o -> {
                                    param.setConfirmedPoints(param.getConfirmedPoints() + o.getSpiltPoint());
                                });
                                param.setCurPeriod(
                                        (int) activateUserNewVector.stream()
                                                .filter(o -> o.getConsumeTime().isBefore(item.getConsumeTime())
                                                        || o.getConsumeTime().equals(item.getConsumeTime())).count()
                                );
                            }
                            param.setExpireStartTime(item.getExpireStartTime());
                            param.setExpireEndTime(item.getExpireEndTime());
                            confirmIncomeDetails.add(param);
                        });
                    }
                    boolean flag = confirmIncomeDetailsService.saveBatch(confirmIncomeDetails);
                    log.error(String.valueOf(flag));
                });
    }

    // 小语种统计数据
    private void statisticsData(String appId) {
        final String businessTagId = this.getBusinessTagId(appId);
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", appId);
        List<StatisticalNew> statisticalNews = statisticalNewService.listByMap(map);
        Vector<ConfirmIncomeStatistics> confirmIncomeStatisticsVector = new Vector<>();
        if (CollectionUtil.isNotEmpty(statisticalNews)) {
            statisticalNews.stream().forEach(item -> {
                ConfirmIncomeStatistics param = new ConfirmIncomeStatistics();
                param.setId(item.getId());
                param.setCreateUser(item.getCreateUser());
                param.setCreateTime(LocalDateTime.now());
                if (StringUtils.isNotEmpty(businessTagId)) {
                    param.setBusinessTagId(Long.valueOf(businessTagId));
                }
                param.setAppId(item.getAppId());
                param.setMonthTime(
                        LocalDate.of(item.getMonthTime().getYear(), item.getMonthTime().getMonthValue(), 2)
                );
                param.setOrderSum(item.getNewOpenId().intValue());
                param.setZaoyuanIncome(BigDecimal.ZERO);
                param.setSalvageValueIncome(BigDecimal.ZERO);
                param.setCashIncome(item.getCashIncome());
                param.setNewOrderConfirmIncome(item.getCurrentConfirmIncome());
                if (item.getAllConfirmIncome().compareTo(BigDecimal.ZERO) == 0) {
                    param.setOldOrderConfirmIncome(item.getConfirmIncome());
                } else {
                    param.setOldOrderConfirmIncome(item.getAllConfirmIncome());
                }
                Map<String, Object> cmap = new HashMap<>();
                cmap.put("month_time", LocalDate.of(item.getMonthTime().getYear(), item.getMonthTime().getMonthValue(), 2));
                cmap.put("app_id", appId);
                cmap.put("is_cur_month", 1);
                List<ConfirmIncomeDetails> confirmIncomeDetailsList = confirmIncomeDetailsService.listByMap(cmap);
                // 未确认收入
                BigDecimal newOrderNoConfirmIncome = confirmIncomeDetailsList.stream()
                        .map(ConfirmIncomeDetails::getLeftNoConfirmIncome)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                param.setNewOrderNoConfirmIncome(newOrderNoConfirmIncome);
                // 确认收入
                BigDecimal newOrderConfirmIncome = confirmIncomeDetailsList.stream()
                        .map(ConfirmIncomeDetails::getCurConfirmIncome)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                param.setNewOrderConfirmIncome(newOrderConfirmIncome);
                param.setOldOrderConfirmIncome(param.getOldOrderConfirmIncome().subtract(newOrderConfirmIncome));
                confirmIncomeStatisticsVector.add(param);
            });
            confirmIncomeStatisticsVector.stream().map(item -> {

                List<StatisticalNew> beforeStatisticalList
                        = statisticalNews.stream()
                        .filter(p -> p.getMonthTime().isBefore(item.getMonthTime())
                                || p.getMonthTime().isEqual(item.getMonthTime()))
                        .collect(Collectors.toList());

                // 现金收入
                BigDecimal a = beforeStatisticalList.stream()
                        .map(StatisticalNew::getAllCashIncome)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal b = beforeStatisticalList.stream()
                        .map(StatisticalNew::getAllConfirmIncome)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                item.setOldOrderNoConfirmIncome(
                        a.subtract(b).subtract(item.getNewOrderNoConfirmIncome())
                );
                return item;
            }).collect(Collectors.toList());
            for (int i = 0; i < confirmIncomeStatisticsVector.size(); i++) {
                if (i == 0) {
                    continue;
                }
                confirmIncomeStatisticsVector.get(i)
                        .setPastNoConfirmIncome(
                                confirmIncomeStatisticsVector.get(i - 1).getNewOrderNoConfirmIncome().add(confirmIncomeStatisticsVector.get(i - 1).getOldOrderNoConfirmIncome())
                        );
            }
        }
        confirmIncomeStatisticsService.saveBatch(confirmIncomeStatisticsVector);
    }

    /**
     * 支付方式转换
     *
     * @param payType
     * @return String
     */
    private String getValueByPayType(Integer payType) {
        String value = "";
        if (payType == null) {
            return value;
        }
        switch (payType) {
            case 1:
                value = "bankpay";
                break;
            case 2:
                value = "alipay";
                break;
            case 3:
                value = "tenpay";
                break;
            case 4:
                value = "unionpay";
                break;
            case 5:
                value = "baitiao";
                break;
            case 6:
                value = "tianmao";
                break;
            case 7:
                value = "lovehaimi";
                break;
            case 8:
                value = "apple";
                break;
            case 9:
                value = "xiaoetong";
                break;
            case 10:
                value = "jdzhuanying";
                break;
            case 11:
                value = "jiameng";
                break;
            case 12:
                value = "jdpay";
                break;
            case 13:
                value = "nadoudaili";
                break;
            case 14:
                value = "weidian";
                break;
            case 15:
                value = "duxiaoman";
                break;
            case 16:
                value = "douyin";
                break;
            case 17:
                value = "axpay";
                break;
            case 18:
                value = "xinyonggongshe";
                break;
            case 19:
                value = "huabeifenqi";
                break;
            case 99:
                value = "remittance";
                break;
        }
        return value;
    }

    private String getAppIdByJpTag(String tag) {
        String appId = "";
        if ("jp".equals(tag)) {
            appId = "8275314362b14956867bf00673a0af33";
        } else if ("os".equals(tag)) {
            appId = "80c5c10b75eb4e61a7bceb02ba649698";
        } else if ("ky".equals(tag)) {
            appId = "06c19ba4da1740fa9169f792b04d92e6";
        } else if ("jz".equals(tag)) {
            appId = "14e8933254dd461f92b18ae0d9294317";
        }
        return appId;
    }

    // 获取统计表id
    private Long getStatisticsId(String monthTime, String appId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id", appId);
        queryWrapper.eq("month_time", monthTime);
        ConfirmIncomeStatistics confirmIncomeStatistics
                = confirmIncomeStatisticsService.getOne(queryWrapper);
        if (Objects.nonNull(confirmIncomeStatistics)) {
            return confirmIncomeStatistics.getId();
        } else {
            ConfirmIncomeStatistics cis = new ConfirmIncomeStatistics();
            cis.setId(IdUtil.getSnowflake(1, 1).nextId());
            cis.setAppId(appId);
            cis.setBusinessTagId(Long.valueOf(this.getBusinessTagId(cis.getAppId())));
            cis.setCreateTime(LocalDateTime.now());
            Date date = DateUtil.parseDate(monthTime);
            LocalDate mt = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, 2);
            cis.setMonthTime(mt);
            confirmIncomeStatisticsService.save(cis);
            return cis.getId();
        }
    }


    private String getBusinessTagId(String appId) {
        String businessTagId = "";
        // 日语
        if ("8275314362b14956867bf00673a0af33".equals(appId)) {
            businessTagId = "1325646987453792256";
        } else if ("06fedeb667084d0f9b6666e8baccc9e1".equals(appId)) {
            // 韩语
            businessTagId = "1319100085069414400";
        } else if ("9058f61b5e814c2ebaa4e062df162235".equals(appId)) {
            // 德语
            businessTagId = "1319035249014865920";
        } else if ("6900443d62c54f5090604f0468a5f20e".equals(appId)) {
            // 法语
            businessTagId = "1319100671957401600";
        } else if ("559d6de1e10e4470b21715ff7041412e".equals(appId)) {
            // 西语
            businessTagId = "1319102858632626176";
        } else if ("887b86de893d476fb83d6d0f9a7fa834".equals(appId)) {
            // 倍普
            businessTagId = "1322095189157740544";
        } else if ("80c5c10b75eb4e61a7bceb02ba649698".equals(appId)) {
            // 留学
            businessTagId = "1325647027379372032";
        } else if ("06c19ba4da1740fa9169f792b04d92e6".equals(appId)) {
            // 考研
            businessTagId = "1325647047147126784";
        } else if ("14e8933254dd461f92b18ae0d9294317".equals(appId)) {
            // 就职
            businessTagId = "1325647007070552064";
        } else if ("72d6c41307e14c478d836072a62db662".equals(appId)) {
            // 微课
            businessTagId = "";
        } else if ("f240292f76bf4bb1a2336f1fa51421c9".equals(appId)) {
            // 青柠五十音
            businessTagId = "";
        }
        return businessTagId;
    }

    private String getBusinessTagIdByJpTag(String tag) {
        String businessTagId = "";
        if ("jp".equals(tag)) {
            businessTagId = "1325646987453792256";
        } else if ("os".equals(tag)) {
            businessTagId = "1325647027379372032";
        } else if ("ky".equals(tag)) {
            businessTagId = "1325647047147126784";
        } else if ("jz".equals(tag)) {
            businessTagId = "1325647007070552064";
        }
        return businessTagId;
    }

    private String getPayMethod(String payType) {
        String payMethod = "";
        if ("京东白条".equals(payType)) {
            payMethod = "jdpay";
        } else if ("京东退费".equals(payType)) {
            payMethod = "jdpay_refund";
        } else if ("分期乐".equals(payType)) {
            payMethod = "fqlpay";
        } else if ("卡密支付".equals(payType)) {
            payMethod = "cardpay";
        } else if ("天猫".equals(payType)) {
            payMethod = "tmall";
        } else if ("天猫退费".equals(payType)) {
            payMethod = "tmall_refund";
        } else if ("微信支付".equals(payType)) {
            payMethod = "wx";
        } else if ("支付宝支付".equals(payType)) {
            payMethod = "alipay";
        } else if ("早元".equals(payType)) {
            payMethod = "zy";
        } else if ("汇款".equals(payType)) {
            payMethod = "remittance";
        } else if ("网银支付".equals(payType)) {
            payMethod = "netbank";
        } else if ("银联支付".equals(payType)) {
            payMethod = "netpay";
        }
        return payMethod;
    }

    public String localDateToString(LocalDate date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");
        return date.format(fmt);
    }
    /*alipay
            axpay
    cardpay
            douyinqudao
    duxiaoman
            foreign
    fqlpay
            haierxiaojin
    huabeifenqi
            jdpay
    jdpay_refund
            jdzhuanying
    jiameng
            jingdongpay
    lovehaimi
            lovehaimi_refund
    nadoudaili
            netbank
    netpay
            nvp
    remittance
            tmall
    tmall_refund
            weidian
    wx
            xiaoetong
    xinyonggongshe
            zy*/

    /*京东白条
            京东退费
    分期乐
            卡密支付
    天猫
            天猫退费
    微信支付
            支付宝支付
    早元
            汇款
    网银支付
            银联支付*/

    /*alipay
            axpay
    cardpay
            douyinqudao
    duxiaoman
            foreign
    fqlpay
            huabeifenqi
    jdpay
            jdpay_refund
    jdzhuanying
            jiameng
    jingdongpay
            lovehaimi
    lovehaimi_refund
            nadoudaili
    netbank
            netpay
    nvp
            remittance
    tmall
            tmall_refund
    weidian
            wx
    xiaoetong
            xinyonggongshe
    zy*/
    /*EBANK(1, "网上银行", "bankpay"),
    ALIPAY(2, "支付宝支付", "alipay"),
    TENPAY(3, "微信支付", "tenpay"),
    UNIONPAY(4, "中国银联支付", "unionpay"),
    BAITIAO(5, "白条支付", "baitiao"),
    TIANMAO(6, "天猫支付", "tianmao"),
    LOVEHAIMI(7, "爱海米支付", "lovehaimi"),
    APPLE(8, "苹果支付", "apple"),
    XIAOETONG(9, "小鹅通支付", "xiaoetong"),
    JDZHUANYING(10, "京东专营店", "jdzhuanying"),
    JIAMENGDIAN(11, "加盟店", "jiameng"),
    JDPAY(12, "京东支付", "jdpay"),
    NADOU(13, "纳豆代理", "nadoudaili"),
    WEIDIAN(14, "微店", "weidian"),
    DUXIAOMAN(15, "度小满", "duxiaoman"),
    DOUYIN(16, "抖音渠道", "douyin"),
    AXPAY(17, "安心支付", "axpay"),
    XINYONGGONGSHE(18, "信用公社", "xinyonggongshe"),
    HUABEI(19, "花呗分期", "huabeifenqi"),
    REMITTANCE(99, "银行汇款", "remittance");*/

        /*1=>'学费',
        2=>'补费',
        3=>'续费',
        4=>'转介绍',
        5=>'转班费',
        6=>'教材费',
        7=>'定金',
        8=>'退费',
        9=>'续转退费',
        10=>'邮费',
        11=>'微店',
        12=>'合作平台',
        13=>'系统产品',
//        14=>'留学',
        15=>'结业回归',
        16=>'充值',
        17=>'留学退费',
//        18=>'早道考研',
        19=>'考研退费',
//        20=>'赴日就职',
        21=>'就职退费',*/

    /*  *//** 枚举值 *//*
    Remittance(0, "汇款"), JD_BaiTiao(1, "京东白条"), TianMao(3, "天猫"),

    *//** 枚举值 *//*
    JingMai(4, "京麦"), AiHaimi(5, "海米"), JD_Pay(6, "京东支付"),

    *//** 枚举值 *//*
    NadouDaili(7, "纳豆代理"), WeiDian(8, "微店"), DuXiaoMan(9, "度小满"),

    *//** 枚举值 *//*
    Xinyugongshe(10, "信用公社"), AXPAY(11, "安心支付"), HUABEI(12, "花呗分期");*/

//            1319035249014865920	德语	1	1160539628843962368	李洪健	2020-10-22 05:58:05
//            1319100085069414400	韩语	1	936907777731862528	彭木木	2020-10-22 10:15:43
//            1319100671957401600	法语	1	1059260063908241408	孙梦馨	2020-10-22 10:18:03
//            1319102858632626176	西语	1	1059260063908241408	孙梦馨	2020-10-22 10:26:44
//            1322095189157740544	倍普	1	911915074749599744	心水涟漪	2020-10-30 16:37:11
//            1325646987453792256	日语	1	911915074749599744	心水涟漪	2020-11-09 11:50:46
//            1325647007070552064	就职	1	911915074749599744	心水涟漪	2020-11-09 11:50:50
//            1325647027379372032	留学	1	911915074749599744	心水涟漪	2020-11-09 11:50:55
//            1325647047147126784	考研	1	911915074749599744	心水涟漪	2020-11-09 11:51:00
//            1325647087332753408	高考日语	1	911915074749599744	心水涟漪	2020-11-09 11:51:10
}
