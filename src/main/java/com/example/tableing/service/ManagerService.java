package com.example.tableing.service;

import com.example.tableing.model.Reserve;
import com.example.tableing.model.Store;
import com.example.tableing.repository.ReserveRepository;
import com.example.tableing.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ManagerService {

    private final ShopRepository shopRepository;
    private final ReserveRepository reserveRepository;

    public Store regist(Store store){
        boolean exists = this.shopRepository.existsByStorename(store.getStorename());
        if(exists){
            throw new RuntimeException("이미 존재하는 매장입니다.");
        }
        Store result = this.shopRepository.save(store);
        return result;
    }

    public List<Reserve> searchReserve(String storename){
        List<Reserve> reserveList = reserveRepository.findAllByStorename(storename);
        return reserveList;
    }

    public Reserve allowReserve(Long id, String status){
        Reserve reserve = reserveRepository.findAllById(id);
        Reserve build = Reserve.builder()
                .id(id)
                .username(reserve.getUsername())
                .storename(reserve.getStorename())
                .arrived(reserve.getArrived())
                .reservedAt(reserve.getReservedAt())
                .status(reserve.getStatus())
                .createdAt(reserve.getCreatedAt())
                .build();
        Reserve save = this.reserveRepository.save(build);
        return save;
    }
}
