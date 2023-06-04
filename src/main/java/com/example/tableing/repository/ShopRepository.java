package com.example.tableing.repository;

import com.example.tableing.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Store,Long> {

    boolean existsByStorename(String storename);
}
