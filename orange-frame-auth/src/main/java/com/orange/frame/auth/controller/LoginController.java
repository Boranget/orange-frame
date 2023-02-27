package com.orange.frame.auth.controller;

import com.orange.frame.common.ResultStatus;
import com.orange.frame.auth.service.RbacService;
import com.orange.frame.common.CommonResult;
import com.orange.frame.common.entity.OrangeTokenInfo;
import com.orange.frame.common.entity.OrangeUser;
import com.orange.frame.redis.redis.RedisConstant;
import com.orange.frame.redis.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * 登录Controller
 */
@RestController
public class LoginController {
    @Autowired
    RbacService rbacService;
    @Autowired
    RedisService redisService;
    /**
     * 登陆方法
     * 使用用户名密码获取token
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/get_token", method = RequestMethod.POST)
    public CommonResult<String> getToken(@RequestBody Map<String, String> requestParam) {
        // 通过传入用户名查找用户信息
        OrangeUser user = rbacService.selectUserWithRoles(requestParam.get("username"));
        // 避免出现搜不到用户导致的空指针异常
        if (user != null) {
            // 获取密码
            String passwordEncode = user.getPassword();
            String passwordParam = requestParam.get("password");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            // 进行密码的验证
            if (bCryptPasswordEncoder.matches(passwordParam, passwordEncode)) {
                if (user.isAccountNonExpired()
                        && user.isAccountNonLocked()
                        && user.isCredentialsNonExpired()
                        && user.isEnabled()
                ) {
                    // 生成token存入redis
                    String token = UUID.randomUUID().toString();
                    // 封装token信息
                    OrangeTokenInfo tokenInfo = new OrangeTokenInfo();
                    tokenInfo.setOrangeUser(user);
                    tokenInfo.setExpireTime(System.currentTimeMillis()+30*60*1000);
                    redisService.hSet(RedisConstant.TOKEN_INFO_KEY, token, tokenInfo);
                    return CommonResult.success(token);
                }
            }
        }

        return CommonResult.getCommonResultByStatus(ResultStatus.AUTHENTICATION_FAILURE, "认证失败,请重新尝试");
    }

    /**
     * 获取tokeninfo
     * 测试用,可删除
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/get_token_info", method = RequestMethod.POST)
    public CommonResult<Object> getTokenInfo(@RequestBody Map<String, String> requestParam) {
        return CommonResult.success(redisService.hGet(RedisConstant.TOKEN_INFO_KEY,requestParam.get("token")));
    }

}
