package com.example.jebal.demo.kakao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Mapper
public class KakaoRepository {
    private SqlSessionTemplate sql;

    public KakaoRepository(SqlSessionTemplate sql) {
        this.sql = sql;
    }


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
