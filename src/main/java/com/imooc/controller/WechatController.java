package com.imooc.controller;

import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2019/10/31.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @RequestMapping("/token")
    public void checkToken(HttpServletRequest request, HttpServletResponse response) {
        //token验证代码段
        try {
            log.info("请求已到达，开始校验token");
            if (StringUtils.isNotBlank(request.getParameter("signature"))) {
                String signature = request.getParameter("signature");
                String timestamp = request.getParameter("timestamp");
                String nonce = request.getParameter("nonce");
                String echostr = request.getParameter("echostr");
                log.info("signature[{}], timestamp[{}], nonce[{}], echostr[{}]", signature, timestamp, nonce, echostr);
                if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                    log.info("数据源为微信后台，将echostr[{}]返回！", echostr);
                    response.getOutputStream().println(echostr);
                }
            }
        } catch (IOException e) {
            log.error("校验出错");
            e.printStackTrace();
        }
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url = "http://wanglc.natapp1.cc/sell/wechat/userInfo";
        //配置
        //调用方法
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, returnUrl);
        log.info("【微信网页授权】 获取code, authorizationUrl = {}", redirectUrl );
        return "redirect:" + redirectUrl;

    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            log.info("【微信网页授权】 获取code, code = {}", code );
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】 {}", e);
            throw  new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();

        //o3hySwfitnZnhtDMdlOzIwSc5TjU
        log.info("【获取openId】 openId, openId = {}", openId );


        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
