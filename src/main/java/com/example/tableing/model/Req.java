package com.example.tableing.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Req {
    @Data
    public static class registStore{
        private String storename;
        private String storelocation;;
        private String storedetail;

        public Store toEntity(){
            return Store.builder()
                    .storename(this.storename)
                    .storedetail(this.storedetail)
                    .storelocation(this.storelocation)
                    .build();
        }
    }
    @Data
    public static class allowReserve{
        private Long id;
        private String status;

        public Reserve toEntity(){
            return Reserve.builder()
                    .id(this.id)
                    .status(this.status)
                    .build();
        }
    }

    @Data
    public static class reserve{
        private String username;
        private String storename;
        private String reservedAt;

        public Reserve toEntity(){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime reserveDateTime = LocalDateTime.parse(this.reservedAt,dateTimeFormatter);
            return Reserve.builder()
                    .username(this.username)
                    .storename(this.storename)
                    .reservedAt(reserveDateTime)
                    .arrived(false)
                    .status("create")
                    .build();
        }
    }

    @Data
    public static class review{
        private String username;
        private String storename;
        private String context;

        public Review toEntity(){
            return Review.builder()
                    .username(this.username)
                    .storename(this.storename)
                    .context(this.context)
                    .build();
        }
    }
}
