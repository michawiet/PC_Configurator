package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Gpu;
import com.pcc.pc_configurator.repositories.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/gpu")
@RequiredArgsConstructor
public class GpuController {
    private final GpuRepository gpuRepo;

    @GetMapping
    public ResponseEntity<Iterable<Gpu>> getAllGpu() {
        return ResponseEntity.ok(gpuRepo.findAll());}
}