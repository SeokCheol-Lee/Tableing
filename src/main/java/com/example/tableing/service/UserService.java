package com.example.tableing.service;

import com.example.tableing.model.Req;
import com.example.tableing.model.Reserve;
import com.example.tableing.model.Review;
import com.example.tableing.model.Store;
import com.example.tableing.repository.ReserveRepository;
import com.example.tableing.repository.ReviewRepository;
import com.example.tableing.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final ShopRepository shopRepository;
    private final ReserveRepository reserveRepository;
    private final ReviewRepository reviewRepository;

    public List<Store> search(){
        List<Store> list = shopRepository.findAll();
        log.info(list.toString());
        return list;
    }

    public Reserve reserve(Req.reserve reserve){
        Reserve result = this.reserveRepository.save(reserve.toEntity());
        return result;
    }

    public Reserve notify(String username){
        Reserve reserve = this.reserveRepository.findAllByUsername(username);
        LocalDateTime currDate = LocalDateTime.now();
        LocalDateTime reserveDate = reserve.getReservedAt();
        if(reserveDate.plusMinutes(-10).isBefore(currDate)){
            throw new RuntimeException("예약 10분전에 도착하지 않으셨습니다.");
        }
        Reserve build = Reserve.builder().id(reserve.getId())
                .username(reserve.getUsername())
                .storename(reserve.getStorename())
                .arrived(true)
                .status(reserve.getStatus())
                .reservedAt(reserve.getReservedAt())
                .createdAt(reserve.getCreatedAt()).build();
        Reserve save = this.reserveRepository.save(build);
        return save;
    }

    public Review createReview(Req.review review){
        Review save = reviewRepository.save(review.toEntity());
        return save;
    }
}
