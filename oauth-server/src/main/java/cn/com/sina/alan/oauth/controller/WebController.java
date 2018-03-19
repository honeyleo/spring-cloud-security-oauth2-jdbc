package cn.com.sina.alan.oauth.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangchao08 on 17/3/6.
 */
@RestController
public class WebController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
    	Enumeration<String> headerNames = request.getHeaderNames();
    	while(headerNames.hasMoreElements()) {
    		String header = headerNames.nextElement();
    		System.out.println(header + ":" + request.getHeader(header));
    	}
        return "hello word!";
    }
}
