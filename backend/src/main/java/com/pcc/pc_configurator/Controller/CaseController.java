package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Case_;
import com.pcc.pc_configurator.repositories.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/case")
@RequiredArgsConstructor
public class CaseController {
    private final CaseRepository caseRepo;

    @GetMapping
    public ResponseEntity<Iterable<Case_>> getAllCases() { return ResponseEntity.ok(caseRepo.findAll());}

}
