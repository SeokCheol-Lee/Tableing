package com.example.tableing.service;

import com.example.tableing.model.Auth;
import com.example.tableing.model.Member;
import com.example.tableing.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user ->" + username));
    }

    public Member register(Auth.SignUp member){
        boolean exists = this.memberRepository.existsByUsername(member.getUsername());
        if(exists){
            throw new RuntimeException("이미 사용 중인 아이디");
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        Member result = this.memberRepository.save(member.toEntity());
        return result;
    }

    public Member authenticate(Auth.SignIn member){
        return null;
    }
}
