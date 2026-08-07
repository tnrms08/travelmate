package com.travelmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  //파라미터가 없는 디폴트 생성자를 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 생성
public class UserSignupRequest {
    private String loginId;
    private String password;
    private String name;
}
