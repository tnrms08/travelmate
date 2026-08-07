package com.travelmate.controller;

import com.travelmate.dto.LoginRequest;
import com.travelmate.dto.UserResponse;
import com.travelmate.dto.UserSignupRequest;
import com.travelmate.entity.User;
import com.travelmate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")   //회원가입
    public UserResponse signup(@RequestBody UserSignupRequest request){
        return userService.signup(request);
    }
    @PostMapping("/users/login")    //로그인
    public UserResponse login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

}
