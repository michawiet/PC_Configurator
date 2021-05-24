package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.PsuDTO;
import com.pcc.pc_configurator.repositories.PsuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/psu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PsuController {
    private final PsuRepository psuRepository;
    private List<PsuDTO> psuDtoList = new ArrayList<>();

    //@GetMapping
    //public ResponseEntity<Iterable<Psu>> getAllPsu() { return ResponseEntity.ok(psuRepository.findAll());}

    @GetMapping("/{id}")
    public PsuDTO getOnePsu(@PathVariable int id) {
        return psuDtoList.get(id);
    }

    @Autowired
    public void psuToDTO(ModelMapper modelMapper) {
        for(var psu : psuRepository.findAll())
            psuDtoList.add(modelMapper.map(psu,PsuDTO.class));
    }

    @GetMapping
    public List<PsuDTO> getPsus() {
        return psuDtoList;
    }
}