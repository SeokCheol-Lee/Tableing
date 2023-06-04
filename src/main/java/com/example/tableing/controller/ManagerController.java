package com.example.tableing.controller;

import com.example.tableing.model.Req;
import com.example.tableing.model.Reserve;
import com.example.tableing.model.Store;
import com.example.tableing.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/manager")
@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    //매장 등록
    /*
    header에 토큰이 필요함
    storename, storelocation, storedetail를 입력받음
     */
    @PostMapping("/regist-store")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addShop(@RequestBody Req.registStore registStore){
        log.info(registStore.toString());
        Store store = this.managerService.regist(registStore);
        return ResponseEntity.ok(store);
    }

    //예약 확인
    /*
    requestParm으로 입력 받기에 url로 입력해주어야 하고,
    어떤 매장의 예약을 확인할 지에 대해서 입력해주어야 함
     */
    @GetMapping("/search-reserve")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> searchReserve(@RequestParam String storename){
        List<Reserve> reserveList = managerService.searchReserve(storename);
        return ResponseEntity.ok(reserveList);
    }
    //예약 수락/거절
    /*
    id, status를 받아서 put으로 입력해줌, id의 경우는 예약 확인을 통해서 확인
    status는 denied와 allow로 입력 받아서 db에 업데이트
     */
    @PutMapping("/allow-reserve")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> allowReserve(
            @RequestBody Req.allowReserve request){
        Reserve reserve = this.managerService.allowReserve(request);
        return ResponseEntity.ok(reserve);
    }
}
