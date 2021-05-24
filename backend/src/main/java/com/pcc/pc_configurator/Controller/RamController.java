package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.RamDTO;
import com.pcc.pc_configurator.repositories.RamRepository;
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
@RequestMapping("/products/ram")
@RequiredArgsConstructor
public class RamController {
    private final RamRepository ramRepository;
    private List<RamDTO> ramDtoList = new ArrayList<>();

    //@GetMapping
    //public ResponseEntity<Iterable<Ram>> getAllRam() { return ResponseEntity.ok(ramRepository.findAll());}

    @GetMapping("/{id}")
    public RamDTO getOneRam(@PathVariable int id) {
        return ramDtoList.get(id);
    }

    @Autowired
    public void ramToDTO(ModelMapper modelMapper) {
        for(var ram : ramRepository.findAll())
            ramDtoList.add(modelMapper.map(ram,RamDTO.class));
    }

    @GetMapping
    public List<RamDTO> getRams() {
        return ramDtoList;
    }
}