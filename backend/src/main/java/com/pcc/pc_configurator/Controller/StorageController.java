package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.StorageDTO;
import com.pcc.pc_configurator.Views;
import com.pcc.pc_configurator.entities.Storage;
import com.pcc.pc_configurator.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @JsonView(Views.Normal.class)
    public StorageDTO getOneStorage(@PathVariable int id) {
        return storageDtoList.get(id);
    }

    @Autowired
    public void storageToDTO(ModelMapper modelMapper) {
        for(int i=0;i<storageRepository.findAll().size();++i)
            storageDtoList.add(modelMapper.map(storageRepository.findAll().get(i),StorageDTO.class));
    }

    @GetMapping
    @JsonView(Views.Normal.class)
    public List<StorageDTO> getOrders() {
        return storageDtoList;
    }
}
