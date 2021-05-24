package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.MotherboardDTO;
import com.pcc.pc_configurator.repositories.MotherboardRepository;
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
@RequestMapping("/products/motherboard")
@RequiredArgsConstructor
public class MotherboardController {
    private final MotherboardRepository motherboardRepository;
    private List<MotherboardDTO> motherboardDtoList = new ArrayList<>();


    //@GetMapping
    //public ResponseEntity<Iterable<Motherboard>> getAllMotherboards() { return ResponseEntity.ok(motherboardRepository.findAll());}

    @GetMapping("/{id}")
    public MotherboardDTO getOneMotherboard(@PathVariable int id) {
        return motherboardDtoList.get(id);
    }

    @Autowired
    public void motherboardToDTO(ModelMapper modelMapper) {
        for(var motherboard : motherboardRepository.findAll())
            motherboardDtoList.add(modelMapper.map(motherboard,MotherboardDTO.class));
    }

    @GetMapping
    public List<MotherboardDTO> getMotherboards() {
        return motherboardDtoList;
    }
}