package com.example.jebal.demo.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;
import com.example.jebal.demo.kakao.KakaoDTO;

import java.util.HashMap;

@Repository
public class KakaoRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public void kakaoinsert(HashMap<String, Object> userInfo) {
        sql.insert("Member.kakaoInsert",userInfo);
    }

    // 정보 확인
    public KakaoDTO findkakao(HashMap<String, Object> userInfo) {
        System.out.println("RN:"+userInfo.get("nickname"));
        System.out.println("RE:"+userInfo.get("email"));
        return sql.selectOne("Member.findKakao", userInfo);
    }

}
