package com.orange.frame.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 引入security只是为了使用其中一些工具
 * 这里将security的自动配置给排除
 * 但似乎排除也会有残留影响
 */
@SpringBootApplication()
public class OrangeFrameRbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeFrameRbacApplication.class, args);
    }

}
