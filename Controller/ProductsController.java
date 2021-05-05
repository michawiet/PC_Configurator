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
    private final ProductRepo productRepo;
    private final CpuRepo cpuRepo;
    private final StorageRepo storageRepo;
    private final CaseRepo caseRepo;
    private final CoolerRepo coolerRepo;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    @GetMapping("/cpu")
    public ResponseEntity<Iterable<Cpu>> getAllCpus() { return ResponseEntity.ok(cpuRepo.findAll());}

    @GetMapping("/storage")
    public ResponseEntity<Iterable<Storage>> getAllStorages() { return ResponseEntity.ok(storageRepo.findAll());}

    @GetMapping("/case")
    public ResponseEntity<Iterable<Case_>> getAllCases() { return ResponseEntity.ok(caseRepo.findAll());}

    @GetMapping("/cooler")
    public ResponseEntity<Iterable<Cooler>> getAllCoolers() { return ResponseEntity.ok(coolerRepo.findAll());}

}
