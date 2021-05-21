package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.Storage;
import com.pcc.pc_configurator.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageRepository storageRepo;

    @GetMapping
    public ResponseEntity<Iterable<Storage>> getAllStorages() { return ResponseEntity.ok(storageRepo.findAll());}
}
