package com.lhj.confirmIncome.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhj.confirmIncome.entity.*;
import com.lhj.confirmIncome.service.*;
import com.lhj.confirmIncome.service.impl.ZdIncomeActualSkinServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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
@RestController
@RequestMapping("/confirmIncome/register-user-new")
public class RegisterUserNewController {

    private List<String> appIds = new ArrayList<>();

    private List<String> busienssTypes = new ArrayList<>();

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
    private ICashCopyService cashCopyService;

    @Autowired
    private IZdIncomeActualSkinService zdIncomeActualSkinService;

    @Autowired
    private IZdIncomeActual2Service zdIncomeActual2Service;

    @Autowired
    private IZdIncomeActualSaveService zdIncomeActualSaveService;

    @Autowired
    private IStyUserGoodsService styUserGoodsService;

    @Autowired
    private INetschoolPayOrderService netschoolPayOrderService;

    /**
     * 处理现金收入表数据
     * @return String
     */
    @GetMapping("dealWithCashData")
    public String dealWith() {
        for (String appId : appIds) {
            this.dealWithData(appId);
        }
        return "success";
    }

    /**
     * 处理确认收入表数据
     * @return String
     */
    @GetMapping("dealWithConfirmData")
    public String dealWithConfirmData() {
        for (String appId : appIds) {
            this.confirmIncomeData(appId);
        }
        return "success";
    }

    /**
     * 处理统计表数据
     * @return String
     */
    @GetMapping("dealWithStatistics")
    public String dealWithStatistics() {
        for (String appId : appIds) {
            this.statisticsData(appId);
        }
        return "success";
    }

    /**
     * 日语现金收入处理
     * @return String
     */
    @GetMapping("dealWithJapanData")
    public String dealWithJapanData() {
        // 现金流水
        Vector<CashIncomeInfo> cashIncomeInfoVector = new Vector<>();
        List<CashCopy> cashCopyList = cashCopyService.list();
        cashCopyList.parallelStream().forEach(item -> {
            log.error(item.getId().toString());
            // 用户商品
            ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
            map.put("uid", item.getCreateUser());
            map.put("goods_id", item.getGoodsId());
            Vector<StyUserGoods> styUserGoodsVector
                    = new Vector<>(styUserGoodsService.listByMap(map));
            // 确认收入数据
            Vector<ZdIncomeActualSave> zdIncomeActualSaves
                    = new Vector<>();
            if (CollectionUtil.isNotEmpty(styUserGoodsVector)) {
                ConcurrentHashMap<String, Object> map2 = new ConcurrentHashMap<>();
                map2.put("ugid", styUserGoodsVector.get(0).getId());
                zdIncomeActualSaves
                        = new Vector<>(zdIncomeActualSaveService.listByMap(map2));
            }
            // 统计数据
            QueryWrapper qw1 = new QueryWrapper();
            qw1.eq("month_time", LocalDate.of(item.getPayTime().getYear(), item.getPayTime().getMonthValue(), 2));
            qw1.eq("app_id", item.getAppId());
            ConfirmIncomeStatistics confirmIncomeStatistics = confirmIncomeStatisticsService.getOne(qw1);

            CashIncomeInfo param = new CashIncomeInfo();
            param.setId(item.getId());
            param.setCreateUser(Long.valueOf(item.getCreateUser()));
            param.setCreateTime(item.getCreateTime());
            param.setStatisticsId(confirmIncomeStatistics.getId());
            param.setMonthTime(LocalDate.of(item.getPayTime().getYear(), item.getPayTime().getMonthValue(), 2));
            param.setBusinessTagId(Long.valueOf(item.getBusinessTagId()));
            param.setAppId(item.getAppId());
            param.setOrderCode(item.getOrderCode());
            param.setGoodsName(item.getGoodsName());
            param.setGoodsId(Long.valueOf(item.getGoodsId()));
            param.setZaoyuan(BigDecimal.valueOf(item.getZaoyuan()));
            param.setSalvageValue(BigDecimal.valueOf(item.getSalvageValue()));
            param.setCash(BigDecimal.valueOf(item.getCash()));
            param.setOrderPrice(BigDecimal.valueOf(item.getOrderPrice()));
            param.setPayMethod(item.getPayMethod());
            param.setPayTime(item.getPayTime());
            param.setConfirmIncomeMethod(1);
            param.setOpenId(Long.valueOf(item.getOpenId()));
            param.setUserName(item.getUserName());
            if (CollectionUtil.isNotEmpty(zdIncomeActualSaves)) {
                param.setServicePeriod(zdIncomeActualSaves.get(0).getPeriodMonth());
                param.setFirstConfirmIncome(
                        zdIncomeActualSaves.stream()
                                .filter(o -> o.getActualDate() != null)
                                .min((e1, e2) -> e1.getActualDate().compareTo(e2.getActualDate()))
                                .get().getActualAmount()
                );
            } else {
                param.setServicePeriod(1);
                param.setFirstConfirmIncome(BigDecimal.ZERO);
            }
            param.setIsZaoyuanCharge(2);
            cashIncomeInfoVector.add(param);
        });
        boolean flag = cashIncomeInfoService.saveBatch(cashIncomeInfoVector);
        log.error(String.valueOf(flag));
        return "success";
    }

    /**
     * 日语确认收入处理
     * @return
     */
    @GetMapping("dealWithJapanConfirmIncome")
    public String dealWithJapanConfirmIncome() {
        List<ZdIncomeActualSave> zdIncomeActualSaveList = zdIncomeActualSaveService.list();
        Vector<ConfirmIncomeDetails> confirmIncomeDetailsVector = new Vector<>();
        zdIncomeActualSaveList.parallelStream().forEach(item -> {
            log.error(item.getId().toString());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", item.getUgid());
            // 用户商品
            StyUserGoods styUserGoods = styUserGoodsService.getOne(queryWrapper);
            // 现金收入记录
            QueryWrapper qw1 = new QueryWrapper();
            qw1.eq("ugid", item.getUgid());
            Vector<ZdIncomeActual2> zdIncomeActual2s = new Vector<>(zdIncomeActual2Service.list(qw1));

            QueryWrapper qw2 = new QueryWrapper();
            qw2.eq("create_user", styUserGoods.getUid());
            qw2.eq("goods_id", styUserGoods.getGoodsId());
//            qw2.eq("cash", item.getTotalAmount());
            Vector<CashIncomeInfo> cashIncomeInfos = new Vector<>(
                    cashIncomeInfoService.list(qw2)
            );
            // 统计数据
            QueryWrapper qw3 = new QueryWrapper();
            qw3.eq("month_time", LocalDate.of(Integer.valueOf(item.getActualYear()), Integer.valueOf(item.getActualMonth()), 2));
            qw3.eq("app_id", this.getAppIdByJpTag(item.getBusinessType()));
            ConfirmIncomeStatistics confirmIncomeStatistics = confirmIncomeStatisticsService.getOne(qw3);

            ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
            map.put("ugid", item.getUgid());
            Vector<ZdIncomeActualSave> cons = new Vector<>(
                    zdIncomeActualSaveService.listByMap(map)
            );
            ConfirmIncomeDetails param = new ConfirmIncomeDetails();
            param.setId(item.getId());
            if (confirmIncomeStatistics != null) {
                param.setStatisticsId(confirmIncomeStatistics.getId());
            } else {
                param.setStatisticsId(0L);
            }
            param.setCreateUser(styUserGoods.getUid());
            param.setCreateTime(LocalDateTime.now());
            param.setBusinessTagId(Long.valueOf(this.getBusinessTagId(this.getAppIdByJpTag(item.getBusinessType()))));
            param.setAppId(this.getAppIdByJpTag(item.getBusinessType()));
            param.setMonthTime(
                    LocalDate.of(Integer.valueOf(item.getActualYear()), Integer.valueOf(item.getActualMonth()), 2)
            );
            if (CollectionUtil.isNotEmpty(cashIncomeInfos)) {
                param.setOrderCode(cashIncomeInfos.get(0).getOrderCode());
                param.setPayMethod(cashIncomeInfos.get(0).getPayMethod());
                param.setPayTime(cashIncomeInfos.get(0).getPayTime());
                param.setUserName(cashIncomeInfos.get(0).getUserName());
            }
            param.setGoodsId(Long.valueOf(styUserGoods.getGoodsId()));
            param.setGoodsName(styUserGoods.getGoodsName());
            param.setCash(item.getTotalAmount());
            param.setOrderPrice(item.getTotalAmount());
            param.setOpenId(styUserGoods.getUid());
            param.setServicePeriod(item.getPeriodMonth());
            if (styUserGoods.getActivateTime().getYear() == Integer.valueOf(item.getActualYear())
                    && styUserGoods.getActivateTime().getMonthValue() == Integer.valueOf(item.getActualMonth())) {
                param.setIsCurMonth(1);
            }
            param.setCurPeriod(
                    (int) cons.stream()
                            .filter(o -> LocalDate.of(Integer.valueOf(o.getActualYear()), Integer.valueOf(o.getActualMonth()), 2)
                                    .isBefore(LocalDate.of(Integer.valueOf(item.getActualYear()), Integer.valueOf(item.getActualMonth()), 2))
                                    || LocalDate.of(Integer.valueOf(o.getActualYear()), Integer.valueOf(o.getActualMonth()), 2)
                                    .equals(LocalDate.of(Integer.valueOf(item.getActualYear()), Integer.valueOf(item.getActualMonth()), 2))).count()
            );
            param.setCurConfirmIncome(item.getActualAmount());
            param.setLeftNoConfirmIncome(item.getActualLeft());
            if (CollectionUtil.isNotEmpty(zdIncomeActual2s) && zdIncomeActual2s.size() == 1) {
                param.setExpireStartTime(zdIncomeActual2s.get(0).getActiveTime());
                param.setExpireEndTime(zdIncomeActual2s.get(0).getExpireTime());
            }
            confirmIncomeDetailsVector.add(param);
        });
        boolean flag = confirmIncomeDetailsService.saveBatch(confirmIncomeDetailsVector);
        log.error(String.valueOf(flag));
        return "success";
    }

    /**
     * dealWithJapanStatistics
     * @return String
     */
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
            param.setBusinessTagId(
                    Long.valueOf(
                            this.getBusinessTagId(
                                    this.getAppIdByJpTag(item.getBusinessType())
                            )
                    )
            );
            param.setAppId(this.getAppIdByJpTag(item.getBusinessType()));
            Date date = DateUtil.parseDate(item.getActualDate()+"-01");
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
        for (int i = 0; i < confirmIncomeStatisticsVector.size(); i++) {
            if (i == 0) {
                continue;
            }
            confirmIncomeStatisticsVector.get(i)
                    .setPastNoConfirmIncome(
                            confirmIncomeStatisticsVector.get(i - 1).getNewOrderNoConfirmIncome().add(confirmIncomeStatisticsVector.get(i - 1).getOldOrderNoConfirmIncome())
                    );
        }
        boolean flag = confirmIncomeStatisticsService.saveBatch(confirmIncomeStatisticsVector);
        log.error(String.valueOf(flag));
        return "success";
    }

    // 小语种现金收入
    private void dealWithData(String appId) {
        final String businessTagId = this.getBusinessTagId(appId);
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", appId);
        List<RegisterUserNew> registerUserNewList = registerUserNewService.listByMap(map);
        Vector<CashIncomeInfo> cashIncomeInfoVector = new Vector<>();

        if (CollectionUtil.isNotEmpty(registerUserNewList)) {
            registerUserNewList.parallelStream()
                    .filter(a -> !"德语组一".equals(a.getOrderId())
                            && !"韩语组一".equals(a.getOrderId()))
                    .forEach(item -> {
                        QueryWrapper queryWrapper = new QueryWrapper<PayOrder>();
                        queryWrapper.eq("order_code", item.getOrderId());
                        PayOrder payOrder = payOrderService.getOne(queryWrapper);
                        log.error(item.getOrderId().toString());
                        ConcurrentHashMap<String, Object> actMap = new ConcurrentHashMap<>();
                        actMap.put("order_id", payOrder.getOrderCode());
                        Vector<ActivateUserNew> activateUserNews = new Vector<ActivateUserNew>(activateUserNewService.listByMap(actMap));
                        CashIncomeInfo cashIncomeInfo = new CashIncomeInfo();
                        cashIncomeInfo.setId(item.getId());
                        cashIncomeInfo.setCreateUser(item.getCreateUser());
                        cashIncomeInfo.setCreateTime(LocalDateTime.now());
                        cashIncomeInfo.setStatisticsId(item.getStatisticalId());
                        cashIncomeInfo.setMonthTime(LocalDate.of(item.getConsumeTime().getYear(), item.getConsumeTime().getMonthValue(), 2));
                        if (StringUtils.isNotEmpty(businessTagId)) {
                            cashIncomeInfo.setBusinessTagId(Long.valueOf(businessTagId));
                        }
                        cashIncomeInfo.setAppId(appId);
                        cashIncomeInfo.setOrderCode(item.getOrderId());
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
                        if (CollectionUtil.isNotEmpty(activateUserNews)) {
                            cashIncomeInfo.setServicePeriod(activateUserNews.get(0).getServiceTime());
                            cashIncomeInfo.setFirstConfirmIncome(
                                    activateUserNews.stream()
                                            .filter(o -> o.getMonthTime() != null)
                                            .min((e1, e2) -> e1.getMonthTime().compareTo(e2.getMonthTime()))
                                            .get().getSpiltPrice()
                            );
                        } else {
                            cashIncomeInfo.setServicePeriod(1);
                            cashIncomeInfo.setFirstConfirmIncome(BigDecimal.ZERO);
                        }
                        cashIncomeInfo.setIsZaoyuanCharge(2);
                        cashIncomeInfoVector.add(cashIncomeInfo);
                    });

        }
        boolean flag = cashIncomeInfoService.saveBatch(cashIncomeInfoVector);
        log.error(String.valueOf(flag));
    }
    // 小语种确认收入
    private void confirmIncomeData(String appId) {
        final String businessTagId = this.getBusinessTagId(appId);
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", appId);
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

    /**
     * 支付方式转换
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
