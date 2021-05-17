package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.*;
import com.pcc.pc_configurator.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepository productRepo;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepo.findAll());
    }
}
