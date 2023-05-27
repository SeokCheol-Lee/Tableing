package com.example.tableing.controller;

import com.example.tableing.model.Store;
import com.example.tableing.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/manager")
@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/regist-store")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addShop(@RequestBody Store request){
        Store store = this.managerService.regist(request);
        return ResponseEntity.ok(store);
    }
}
