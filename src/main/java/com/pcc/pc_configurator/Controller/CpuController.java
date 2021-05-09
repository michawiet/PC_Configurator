package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Cpu;
import com.pcc.pc_configurator.repositories.CpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/cpu")
@RequiredArgsConstructor
public class CpuController {
    private final CpuRepository cpuRepo;

    @GetMapping
    public ResponseEntity<Iterable<Cpu>> getAllCpus() { return ResponseEntity.ok(cpuRepo.findAll());}
}
