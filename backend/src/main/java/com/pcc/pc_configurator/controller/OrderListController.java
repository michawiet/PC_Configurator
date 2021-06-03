package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.Entities.OrderList;
import com.pcc.pc_configurator.Repositories.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderlist")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderListController {
    private final OrderListRepository orderListRepository;

    @GetMapping
    public ResponseEntity<Iterable<OrderList>> getAllOrders() { return ResponseEntity.ok(orderListRepository.findAll()); }
}
