package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.OrderDTO;
import com.pcc.pc_configurator.repositories.OrderRepository;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<OrderDTO> allOrders() {

        List<OrderDTO> orderDtoList = new ArrayList<>();

        for (var order : orderRepository.findAll()) {
            orderDtoList.add(modelMapper.map(order,OrderDTO.class));
        }

        return orderDtoList;
    }
}
