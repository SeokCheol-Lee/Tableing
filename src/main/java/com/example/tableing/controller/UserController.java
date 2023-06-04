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

    //매장 검색
    @GetMapping("/search-store")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchStore(){
        List<Store> store = userService.search();
        return ResponseEntity.ok(store);
    }

    //예약하기
    /*
    어느 매장을 예약할지 매장 이름, 사용자 이름, 예약 일자입력
    예약 일자의 format은 ex. 202306041200
     */
    @PostMapping("/reserve")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reserveStore(
            @RequestBody Req.reserve reserve){
        Reserve result = userService.reserve(reserve);
        return ResponseEntity.ok(result);
    }

    //도착확인
    /*
    사용자가 키오스크를 통해 도착했다고 보낼 때, username을 받아 전송
     */
    @PutMapping("/arrive")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> notifyArrive(@RequestParam String username){
        Reserve notify = userService.notify(username);
        return ResponseEntity.ok(notify);
    }

    //리뷰 작성
    /*
    리뷰작성자의 이름, 매장이름, 리뷰 코멘트를 입력받아 작성
     */
    @PostMapping("/review")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createReview(
            @RequestBody Req.review review){
        Review serviceReview = userService.createReview(review);
        return ResponseEntity.ok(serviceReview);
    }

}
