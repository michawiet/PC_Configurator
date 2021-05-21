package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Ram;
import com.pcc.pc_configurator.repositories.RamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/ram")
@RequiredArgsConstructor
public class RamController {
    private final RamRepository ramRepository;

    @GetMapping
    public ResponseEntity<Iterable<Ram>> getAllRam() { return ResponseEntity.ok(ramRepository.findAll());}
}