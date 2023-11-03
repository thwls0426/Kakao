package com.example.jebal.demo.kakao;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class KakaoController {

    private final KakaoService kakao;

    @RequestMapping(value = "/katalk")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/katalk/callback")
    public String login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("controller access_token : " + access_Token);

//        if (userInfo.get("email") != null) {
//            session.setAttribute("userId", userInfo.get("email"));
//            session.setAttribute("access_Token", access_Token);
//        }

        return "redirect:/success.html";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
//        kakao.Logout((String)session.getAttribute("access_token"));
//        session.invalidate(); //>> 로그아웃


        String access_Token = (String) session.getAttribute("access_Token");

        if (access_Token != null && !"".equals(access_Token)) {
            kakao.Logout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
        } else {
            System.out.println("access_Token is null");
            // >> 엑세스 토큰 없다고 출력


            return "redirect:/logout_success.html";
        }

//    @RequestMapping(value = "/kakao/unlink")
//        public String unlink(HttpSession session){
//            kakao.unlink((String) session.getAttribute("access_token"));
//            session.invalidate();
//            return "redirect:/unlink_success.html";
//        }
//
//    }
        return access_Token;
    }
}
