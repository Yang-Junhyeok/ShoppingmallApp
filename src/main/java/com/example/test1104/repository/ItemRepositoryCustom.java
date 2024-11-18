package com.example.test1104.repository;

import com.example.test1104.dto.ItemSearchDto;
import com.example.test1104.dto.MainItemDto;
import com.example.test1104.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
