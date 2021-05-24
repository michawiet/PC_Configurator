package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.CpuDTO;
import com.pcc.pc_configurator.Temp;
import com.pcc.pc_configurator.entities.Cpu;
import com.pcc.pc_configurator.repositories.CpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/products/cpu")
@RequiredArgsConstructor
public class CpuController {
    private final CpuRepository cpuRepository;
    private List<CpuDTO> cpuDtoList = new ArrayList<>();
    //@GetMapping
    //public ResponseEntity<Iterable<Cpu>> getAllCpus() { return ResponseEntity.ok(cpuRepository.findAll());}
    //@GetMapping("/{id}")
    //public ResponseEntity<Optional<Cpu>> getOneCpu(@PathVariable Long id) {
    //    return ResponseEntity.ok(cpuRepository.findById(id));
    //}
    @GetMapping("/{id}")
    public CpuDTO getOneCpu(@PathVariable int id) {
        return cpuDtoList.get(id);
    }

    @Autowired
    public void cpuToDTO(ModelMapper modelMapper) {
        var repo = cpuRepository.findAll();
        for(int i=0;i<repo.size();++i)
        cpuDtoList.add(modelMapper.map(repo.get(i),CpuDTO.class));
    }

    @GetMapping("/test/{id}")
    public List<Temp> test(@PathVariable long id) { return cpuRepository.findByIdTemp(id);}
    
    @GetMapping
    public List<CpuDTO> getOrders() {
        return cpuDtoList;
    }
}
