package com.example.jebal.demo.kakao;

import lombok.Data;
import org.springframework.web.bind.annotation.Mapping;

@Data
public class KakaoDTO {
    private long k_number;
    private String k_name;
    private String k_email;

}

