package com.example.jebal.demo.kakao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KakaoMapper {

    @Select("SELECT * FROM h2_kakao_table WHERE k_name = #{nickname} AND k_email = #{email}")
    KakaoDTO findKakao(@Param("nickname") String nickname, @Param("email") String email);

    @Insert("INSERT INTO h2_kakao_table (k_name, k_email) VALUES (#{nickname}, #{email})")
    void kakaoInsert(@Param("nickname") String nickname, @Param("email") String email);
}