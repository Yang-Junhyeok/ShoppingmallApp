package com.example.test1104.service;

import com.example.test1104.dto.OrderDto;
import com.example.test1104.dto.OrderHistDto;
import com.example.test1104.dto.OrderItemDto;
import com.example.test1104.entity.*;
import com.example.test1104.repository.ItemImgRepository;
import com.example.test1104.repository.ItemRepository;
import com.example.test1104.repository.MemberRepository;
import com.example.test1104.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;

    public Long order(OrderDto orderDto, String username){
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByUsername(username);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String username, Pageable pageable){
        List<Order> orders = orderRepository.findOrders(username, pageable);
        Long totalCount = orderRepository.countOrder(username);

        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        for(Order order : orders){
            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems){
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(),"Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }
            orderHistDtos.add(orderHistDto);
        }
        return new PageImpl<OrderHistDto>(orderHistDtos,pageable,totalCount);
    }
}
