package com.example.tableing.controller;

import com.example.tableing.model.Reserve;
import com.example.tableing.model.Review;
import com.example.tableing.model.Store;
import com.example.tableing.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
                .arrived(false)
                .status("create")
                .reservedAt(reservedAt)
                .build();
        Reserve result = userService.reserve(reserve);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/arrive")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> notifyArrive(@RequestBody String username){
        Reserve notify = userService.notify(username);
        return ResponseEntity.ok(notify);
    }

    @PostMapping("/review")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createReview(
            @RequestBody String username,String storename,
            String context){
        Review review = Review.builder()
                .username(username)
                .storename(storename)
                .context(context)
                .build();
        Review serviceReview = userService.createReview(review);
        return ResponseEntity.ok(serviceReview);
    }

}
