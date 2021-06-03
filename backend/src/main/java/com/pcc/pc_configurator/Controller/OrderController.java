package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.Entities.Order_;
import com.pcc.pc_configurator.Repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderRepository orderRepo;

    @GetMapping
    public ResponseEntity<Iterable<Order_>> getAllOrders() {
        return ResponseEntity.ok(orderRepo.findAll());
    }
}
