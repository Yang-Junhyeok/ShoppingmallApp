package com.example.test1104.entity;

import com.example.test1104.constant.ItemSellStatus;
import com.example.test1104.dto.ItemFormDto;
import com.example.test1104.exception.OutOfSttockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품 코드
    @Column(nullable = false, length = 50)
    private String itemNm; //상품명
    @Column(name="price", nullable = false)
    private int price; //가격
    @Column(nullable = false)
    private int stockNumber;
    @Lob
    @Column(nullable = false)//재고수량
    private String itemDetail; //상세설명
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if (restStock<0){
            throw new OutOfSttockException("상품의 재고가 부족합니다., (현재 재고수량: "+ this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }
}
