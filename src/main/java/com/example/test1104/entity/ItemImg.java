package com.example.test1104.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item_img")
@Getter
@Setter
public class ItemImg extends BaseEntity{

    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; //저장되는 이미지명

    private String oriImgName; //원본이미지명

    private String imgUrl; //이미지 저장 경로

    private String repimgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName,String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
