package com.example.tableing.repository;

import com.example.tableing.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Store,Long> {

    boolean existsByStorename(String storename);
}
