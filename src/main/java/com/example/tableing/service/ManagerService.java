package com.example.tableing.service;

import com.example.tableing.model.Req;
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

    public Store regist(Req.registStore req){
        boolean exists = this.shopRepository.existsByStorename(req.getStorename());
        if(exists){
            throw new RuntimeException("이미 존재하는 매장입니다.");
        }
        log.info(req.toString());
        Store result = shopRepository.save(req.toEntity());
        return result;
    }

    public List<Reserve> searchReserve(String storename){
        List<Reserve> reserveList = reserveRepository.findAllByStorename(storename);
        return reserveList;
    }

    public Reserve allowReserve(Req.allowReserve request){
        Reserve reserve = reserveRepository.findAllById(request.getId());
        Reserve build = Reserve.builder()
                .id(request.getId())
                .username(reserve.getUsername())
                .storename(reserve.getStorename())
                .arrived(reserve.getArrived())
                .reservedAt(reserve.getReservedAt())
                .status(request.getStatus())
                .createdAt(reserve.getCreatedAt())
                .build();
        Reserve save = this.reserveRepository.save(build);
        return save;
    }
}
