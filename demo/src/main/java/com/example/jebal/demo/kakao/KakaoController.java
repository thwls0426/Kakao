package com.example.jebal.demo.kakao;


import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
@MapperScan
public class KakaoController {

    private final KakaoService kakao;

    @RequestMapping(value = "/katalk")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/katalk/callback")
    public String login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getAccessToken(code);

        KakaoDTO userInfo = kakao.getUserInfo(access_Token);
        System.out.println("controller access_token : " + access_Token);

//        if (userInfo.get("email") != null) {
//            session.setAttribute("userId", userInfo.get("email"));
//            session.setAttribute("access_Token", access_Token);
//        }

        return "redirect:/success.html";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        String access_Token = (String) session.getAttribute("access_Token");

        if (access_Token != null && !"".equals(access_Token)) {
            kakao.Logout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
        } else {
            System.out.println("access_Token is null");
        }
        return "redirect:/logout_success.html";
    }

    @RequestMapping(value = "/unlink")
    public String unlink(HttpSession session) {
        String access_Token = (String) session.getAttribute("access_Token");

        if (access_Token != null && !"".equals(access_Token)) {
            kakao.unlink(access_Token);
            session.invalidate();
        } else {
            System.out.println("access_Token is null");
        }
        return "redirect:/unlink_success.html";
    }
//=========================머노==============================
    @Autowired
    private HttpSession session;

    @RequestMapping(value="/katalk/login", method= RequestMethod.GET)
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {
        System.out.println("#########" + code);
        String access_Token = kakao.getAccessToken(code);
        KakaoDTO userInfo = kakao.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.getK_name());
        System.out.println("###email#### : " + userInfo.getK_email());

        session.invalidate();

        session.setAttribute("kakaoN", userInfo.getK_name());
        session.setAttribute("kakaoE", userInfo.getK_email());

        return "redirect:/login.html";
    }
}
