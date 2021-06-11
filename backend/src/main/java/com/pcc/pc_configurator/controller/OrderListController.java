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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderlist")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderListController {
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List< Map<String, Object> > allOrderLists(@RequestParam("email") String email) {

        List<OrderListDTO> orderDtoList = new ArrayList<>();

        for (var orderList : orderListRepository.findAll()) {
            if (orderList.getOrder_().getUser().getEmail().equals(email)) {
                orderDtoList.add(modelMapper.map(orderList, OrderListDTO.class));
            }
        }

        List< Map<String, Object> > response = new ArrayList<>();

        if (!orderDtoList.isEmpty()) {
            for (int i = 0; i < orderDtoList.size(); ++i) {
                Map<String, Object> map = new HashMap<>();
                map.put("orderId", orderDtoList.get(i).getOrderId());
                map.put("date", orderDtoList.get(i).getDate());
                int finalI = i;
                map.put("products", orderDtoList.stream()
                        .filter(p -> (p.getOrderId() == orderDtoList.get(finalI).getOrderId()))
                        .collect(Collectors.toList()));
                response.add(map);
            }
            return response;
        }

        return response;
    }


}
