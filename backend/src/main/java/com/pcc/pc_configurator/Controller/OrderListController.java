package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.OrderList;
import com.pcc.pc_configurator.repositories.OrderListRepository;
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
