package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.PsuDTO;
import com.pcc.pc_configurator.repositories.PsuRepository;
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
@RequestMapping("/products/psu")
@RequiredArgsConstructor
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
        var repo = psuRepository.findAll();
        for(int i=0;i<repo.size();++i)
            psuDtoList.add(modelMapper.map(repo.get(i),PsuDTO.class));
    }

    @GetMapping
    public List<PsuDTO> getOrders() {
        return psuDtoList;
    }
}