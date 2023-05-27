package com.example.tableing.service;

import com.example.tableing.model.Reserve;
import com.example.tableing.model.Store;
import com.example.tableing.repository.ReserveRepository;
import com.example.tableing.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final ShopRepository shopRepository;
    private final ReserveRepository reserveRepository;

    public Store search(String storename){
        boolean exists = this.shopRepository.existsByStorename(storename);
        if(!exists){
            throw new RuntimeException("존재하지 않는 매장입니다.");
        }
        Store result = this.shopRepository.findAllByStorename(storename);
        return result;
    }

    public Reserve reserve(Reserve reserve){
        Reserve result = this.reserveRepository.save(reserve);
        return result;
    }
}
