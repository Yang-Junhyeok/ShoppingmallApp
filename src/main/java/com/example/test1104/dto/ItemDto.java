package com.example.test1104.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
