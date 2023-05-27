package com.example.tableing.service;

import com.example.tableing.model.Store;
import com.example.tableing.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ManagerService {

    private final ShopRepository shopRepository;

    public Store regist(Store store){
        boolean exists = this.shopRepository.existsByStorename(store.getStorename());
        if(exists){
            throw new RuntimeException("이미 존재하는 매장입니다.");
        }
        Store result = this.shopRepository.save(store);
        return result;
    }
}
