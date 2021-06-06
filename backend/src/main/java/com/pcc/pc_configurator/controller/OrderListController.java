package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.OrderListDTO;
import com.pcc.pc_configurator.repositories.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderlist")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderListController {
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public Map<String, Object> allOrderLists(@RequestParam("email") String email) {

        List<OrderListDTO> orderDtoList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        for (var orderList : orderListRepository.findAll()) {
            if (orderList.getOrder_().getUser().getEmail().equals(email)) {
                orderDtoList.add(modelMapper.map(orderList, OrderListDTO.class));
            }
        }
        if (!orderDtoList.isEmpty()) {
            map.put("orderId", orderDtoList.get(0).getOrderId());
            map.put("date", orderDtoList.get(0).getDate());
            map.put("products", orderDtoList);
            return map;
        }
        return map;
    }
}
