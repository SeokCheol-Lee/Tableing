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

    @PostMapping("/regist-store")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addShop(@RequestBody Req.registStore registStore){
        log.info(registStore.toString());
        Store store = this.managerService.regist(registStore);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/search-reserve")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> searchReserve(@RequestParam String storename){
        List<Reserve> reserveList = managerService.searchReserve(storename);
        return ResponseEntity.ok(reserveList);
    }

    @PutMapping("/allow-reserve")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> allowReserve(
            @RequestBody Req.allowReserve request){
        Reserve reserve = this.managerService.allowReserve(request);
        return ResponseEntity.ok(reserve);
    }
}
