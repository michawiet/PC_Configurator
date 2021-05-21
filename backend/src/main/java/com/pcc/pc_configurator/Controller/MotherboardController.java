package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Motherboard;
import com.pcc.pc_configurator.repositories.MotherboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/motherboard")
@RequiredArgsConstructor
public class MotherboardController {
    private final MotherboardRepository motherboardRepository;

    @GetMapping
    public ResponseEntity<Iterable<Motherboard>> getAllMotherboards() { return ResponseEntity.ok(motherboardRepository.findAll());}
}