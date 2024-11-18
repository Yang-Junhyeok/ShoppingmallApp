package com.example.test1104.controller;

import com.example.test1104.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/Ex02")
    public String thymeleafExample02(Model model){
        ItemDto itemDto = new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemNm("테스트 상품1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());
        model.addAttribute("itemDto", itemDto);
        return "thymeleaf/thymeleafEx02";
    }

    @GetMapping("/Ex03")
    public String thymeleafExample03(Model model){
        List<ItemDto> itemDtolist = new ArrayList<>();

        for(int i=1; i<=10; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemNm("테스트 상품" + i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtolist.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtolist);
        return "thymeleaf/thymeleafEx03";
    }
    @GetMapping("/Ex04")
    public String thymeleafExample04(Model model){
        List<ItemDto> itemDtolist = new ArrayList<>();

        for(int i=1; i<=10; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemNm("테스트 상품" + i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtolist.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtolist);
        return "thymeleaf/thymeleafEx04";
    }
    @GetMapping("/Ex05")
    public String thymeleafExample05(){
        return "thymeleaf/thymeleafEx05";
    }

    @GetMapping("/Ex06")
    public String thymeleafExample06(String param1, String param2, Model model){
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleaf/thymeleafEx06";
    }
}
