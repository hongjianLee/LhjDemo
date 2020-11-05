package com.lhj.openFeignService;

import com.lhj.lhjcore.IService.IUserService;
import com.lhj.lhjcore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihongjian
 */
@RestController
public class OpenFeignService{
	@Autowired
	private IUserService userService;

	@GetMapping("/echo/{str}")
	public String echo(@PathVariable String str) {
		return "provider-" + str;
	}

	@GetMapping("/getUser")
	public List<User> getUser(@RequestParam("uesrId") Long userId) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("id", userId);
		return userService.listByMap(map);
	}
}
