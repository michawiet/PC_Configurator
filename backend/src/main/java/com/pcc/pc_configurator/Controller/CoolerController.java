package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Cooler;
import com.pcc.pc_configurator.repositories.CoolerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/cooler")
@RequiredArgsConstructor
public class CoolerController {
    private final CoolerRepository coolerRepo;

    @GetMapping
    public ResponseEntity<Iterable<Cooler>> getAllCoolers() { return ResponseEntity.ok(coolerRepo.findAll());}
}
