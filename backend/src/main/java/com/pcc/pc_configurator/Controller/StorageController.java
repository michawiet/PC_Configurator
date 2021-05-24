package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.StorageDTO;
import com.pcc.pc_configurator.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageRepository storageRepository;
    private List<StorageDTO> storageDtoList = new ArrayList<>();

   //@GetMapping
   //public ResponseEntity<Iterable<Storage>> getAllStorages() { return ResponseEntity.ok(storageRepo.findAll());}

    @GetMapping("/{id}")
    public StorageDTO getOneStorage(@PathVariable int id) {
        return storageDtoList.get(id);
    }

    @Autowired
    public void storageToDTO(ModelMapper modelMapper) {
        for(var storage : storageRepository.findAll())
            storageDtoList.add(modelMapper.map(storage,StorageDTO.class));
    }

    @GetMapping
    public List<StorageDTO> getStorages() {
        return storageDtoList;
    }
}
