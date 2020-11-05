package com.lhj.controller;


import com.alibaba.fastjson.JSON;
import com.lhj.lhjRocketmqp.IService.IMqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李洪健
 * @since 2020-11-03
 */
@Slf4j
@RestController
@RequestMapping("/roketmqp")
public class RocketMqpController {

    @Reference(version = "${dubbo.consumer.versoin}")
    private IMqProducerService mqProducerService;

    @GetMapping("message/{id}")
    public String get(@PathVariable Long id) {
        String resultMsg = mqProducerService.sendMessageToMQ("test", "test",
                JSON.toJSONString(id));
        log.debug(resultMsg);
        return resultMsg;
    }

}
