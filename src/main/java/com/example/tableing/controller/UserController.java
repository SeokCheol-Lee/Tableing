package com.example.tableing.controller;

import com.example.tableing.model.Reserve;
import com.example.tableing.model.Store;
import com.example.tableing.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/search-store")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchStore(@RequestBody String storename){
        Store store = userService.search(storename);
        return ResponseEntity.ok(store);
    }

    @PostMapping("/reserve")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reserveStore(
            @RequestBody String username, String storename, LocalDateTime reservedAt){
        Reserve reserve = Reserve.builder()
                .username(username)
                .storename(storename)
                .reservedAt(reservedAt)
                .build();
        Reserve result = userService.reserve(reserve);
        return ResponseEntity.ok(result);
    }
}
