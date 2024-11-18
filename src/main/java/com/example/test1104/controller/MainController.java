package com.example.test1104.controller;

import com.example.test1104.dto.ItemSearchDto;
import com.example.test1104.dto.MainItemDto;
import com.example.test1104.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequiredArgsConstructor
@Controller
public class MainController {
    private final ItemService itemService;

    @GetMapping(value = "/")
    public String mainPage(ItemSearchDto itemSearchDto, Model model, @RequestParam(name = "page", defaultValue = "0") Integer page){

        Pageable pageable = PageRequest.of(page, 6);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
}
