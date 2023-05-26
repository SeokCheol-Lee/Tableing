package com.example.tableing.service;

import com.example.tableing.model.Store;
import com.example.tableing.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public Store regist(Store store){
        boolean exists = this.managerRepository.existsByStorename(store.getStorename());
        if(exists){
            throw new RuntimeException("이미 존재하는 매장입니다.");
        }
        Store result = this.managerRepository.save(store);
        return result;
    }
}
