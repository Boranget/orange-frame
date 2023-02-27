package com.orange.frame.hello.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import static com.alibaba.nacos.shaded.org.checkerframework.checker.units.UnitsTools.s;

@RestController
public class HelloController {
    @Value("${server.port}")
    String port;
    @RequestMapping(value = "/hello")
    public String hello(HttpServletRequest httpServletRequest) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("==================parame=================\n");
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        sb.append(JSONObject.toJSONString(parameterMap));
        sb.append("==================body=================\n");
        BufferedReader reader = httpServletRequest.getReader();

        String line = reader.readLine();
        while (line!=null){
            sb.append(line);
            line = reader.readLine();
        }

        sb.append("==================headers=================\n");
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            sb.append(element+":"+httpServletRequest.getHeader(element));
        }
        System.out.println(sb.toString());
        return "Port "+port+": Hello World";
    }
}
