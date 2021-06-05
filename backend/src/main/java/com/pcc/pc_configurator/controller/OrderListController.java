package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.OrderListDTO;
import com.pcc.pc_configurator.Repositories.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orderlist")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderListController {
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<OrderListDTO> allOrderLists() {

        List<OrderListDTO> orderDtoList = new ArrayList<>();

        for (var orderList : orderListRepository.findAll()) {
            orderDtoList.add(modelMapper.map(orderList, OrderListDTO.class));
        }

        return orderDtoList;
    }
}
