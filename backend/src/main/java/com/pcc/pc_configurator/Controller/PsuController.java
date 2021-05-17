package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Psu;
import com.pcc.pc_configurator.repositories.PsuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/psu")
@RequiredArgsConstructor
public class PsuController {
    private final PsuRepository psuRepository;

    @GetMapping
    public ResponseEntity<Iterable<Psu>> getAllPsu() { return ResponseEntity.ok(psuRepository.findAll());}
}