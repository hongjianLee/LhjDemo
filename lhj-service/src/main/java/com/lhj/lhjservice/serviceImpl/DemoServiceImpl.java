package com.lhj.lhjservice.serviceImpl;

import com.lhj.lhjcore.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${dubbo.provider.version}", timeout = 10000)
public class DemoServiceImpl implements DemoService {

    @Override
    public String getMessage(String words) {
        return words;
    }
}
