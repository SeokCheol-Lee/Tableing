package com.example.tableing.repository;

import com.example.tableing.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {
    Reserve findAllByUsername(String username);

    List<Reserve> findAllByStorename(String storename);

    Reserve findAllById(Long id);
}
