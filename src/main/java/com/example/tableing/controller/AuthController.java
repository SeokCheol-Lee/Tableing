package com.example.tableing.controller;

import com.example.tableing.model.Auth;
import com.example.tableing.model.Member;
import com.example.tableing.security.TokenProvider;
import com.example.tableing.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    //회원가입
    /*
    username, password, role을 받습니다.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request){
        Member result = this.memberService.register(request);
        return ResponseEntity.ok(result);
    }

    //로그인
    /*
    username과 password를 입력받습니다.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request){
        Member mem = this.memberService.authenticate(request);
        String token = this.tokenProvider.generateToken(mem.getUsername(), mem.getRole());
        return ResponseEntity.ok(token);
    }
}
