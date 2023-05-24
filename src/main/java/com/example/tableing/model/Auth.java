package com.example.tableing.model;

import lombok.Data;

public class Auth {

    @Data
    public static class SignIn{
        private String username;
        private String password;
    }

    @Data
    public static class SignUp{
        private String username;
        private String password;
        private String role;

        public Member toEntity(){
            return Member.builder()
                    .username(this.username)
                    .password(this.password)
                    .role(this.role)
                    .build();
        }
    }
}
