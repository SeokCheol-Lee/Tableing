package com.example.tableing.controller;

import com.example.tableing.model.Req;
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
import java.util.List;

@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/search-store")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchStore(){
        List<Store> store = userService.search();
        return ResponseEntity.ok(store);
    }

    @PostMapping("/reserve")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reserveStore(
            @RequestBody Req.reserve reserve){
        Reserve result = userService.reserve(reserve);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/arrive")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> notifyArrive(@RequestParam String username){
        Reserve notify = userService.notify(username);
        return ResponseEntity.ok(notify);
    }

    @PostMapping("/review")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createReview(
            @RequestBody Req.review review){
        Review serviceReview = userService.createReview(review);
        return ResponseEntity.ok(serviceReview);
    }

}
