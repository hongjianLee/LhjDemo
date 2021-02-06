package com.lhj.confirmIncome.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhj.confirmIncome.entity.ActivateUserNew;
import com.lhj.confirmIncome.entity.RegisterUserNew;
import com.lhj.confirmIncome.service.IActivateUserNewService;
import com.lhj.confirmIncome.service.IRegisterUserNewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class BeipuDealWithDataController {

    @Autowired
    private IRegisterUserNewService registerUserNewService;

    @Autowired
    private IActivateUserNewService activateUserNewService;

    @GetMapping("beipu")
    public String beipuDealWith(@RequestParam("orderIds") String orderIds) {
        List<String> orderIdList = Arrays.asList(orderIds.split(",").clone());
        orderIdList.stream().forEach(item -> {
            log.error(item);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("order_id", item);
            List<RegisterUserNew> entitys = registerUserNewService.list(queryWrapper);
            RegisterUserNew entity = entitys.get(0);
            entity.setConsumePrice(
                    entitys.stream().map(RegisterUserNew::getConsumePrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
            );
            ActivateUserNew act = new ActivateUserNew();
            act.setId(IdUtil.getSnowflake(1, 1).nextId());
            act.setStatisticalId(1357119802282852352L);
            act.setOrderId(item);
            act.setConsumePrice(entity.getConsumePrice());
            act.setConsumeTime(LocalDateTime.now());
            act.setPayTime(entity.getConsumeTime());
            act.setGoodsId(entity.getGoodsId());
            act.setGoodsName(entity.getGoodsName());
            act.setCreateUser(entity.getCreateUser());
            act.setCreateName(entity.getCreateName());
            act.setAppId("887b86de893d476fb83d6d0f9a7fa834");
            act.setServiceTime(1);
            act.setSpiltPrice(entity.getConsumePrice());
            act.setMonthTime(LocalDate.of(2021, 1, 2));
            act.setIsCurrent(2);
            act.setAllSpiltPrice(entity.getConsumePrice());
            act.setNoPrice(BigDecimal.ZERO);
            act.setSpiltPoint(0);
            act.setAllPoint(0);
            activateUserNewService.save(act);
        });
        return "over";
    }
}
