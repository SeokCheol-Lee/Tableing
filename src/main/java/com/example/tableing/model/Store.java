package com.example.tableing.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "STORE")
@EntityListeners(AuditingEntityListener.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storename;
    private String storelocation;
    private String storedetail;
    @CreatedDate
    private LocalDateTime createdat;

    @Builder
    public Store(String storename, String storelocation, String storedetail){
        this.storename = storename;
        this.storelocation = storelocation;
        this.storedetail = storedetail;
    }
}
