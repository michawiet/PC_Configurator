package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.OrderDTO;
import com.pcc.pc_configurator.DTO.OrderListDTO;
import com.pcc.pc_configurator.repositories.OrderListRepository;
import com.pcc.pc_configurator.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/userOrders")
    public List<Map<String, Object>> userOrders(@RequestParam String email) {
        List<Map<String, Object>> objectList = new ArrayList<>();
        List<OrderDTO> orderDtoList = new ArrayList<>();
        Map<Long, List<OrderListDTO>> tempMap = new HashMap<>();

        for (var order : orderRepository.findAll()) {
            if(order.getUser().getEmail().equals(email))
                orderDtoList.add(modelMapper.map(order,OrderDTO.class));
        }
        for(var o : orderDtoList)
            tempMap.put(o.getId(), new ArrayList<>());

        for (var orderList : orderListRepository.findAll()) {
            if(tempMap.containsKey(orderList.getOrderId())) {
                tempMap.get(orderList.getOrderId()).add(modelMapper.map(orderList, OrderListDTO.class));
            }
        }
        for (var order : orderDtoList) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("date", order.getDate());
            objectMap.put("orderId", order.getId());
            objectMap.put("products", tempMap.get(order.getId()));
            objectMap.put("totalPrice", tempMap.get(order.getId()).stream().map(o -> o.getProduct().getPrice() * o.getQuantity()).mapToDouble(Float::doubleValue).sum());
            objectList.add(objectMap);
        }

        //orderId
        //orderList
        //totalPrice
        //map.put()
        //map.put("order", orderListDtoList);
        return objectList;
    }

    @GetMapping
    public List<OrderDTO> allOrders() {

        List<OrderDTO> orderDtoList = new ArrayList<>();

        for (var order : orderRepository.findAll()) {
            orderDtoList.add(modelMapper.map(order,OrderDTO.class));
        }

        return orderDtoList;
    }
}
