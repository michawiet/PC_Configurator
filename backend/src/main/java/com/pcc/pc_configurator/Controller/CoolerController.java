package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.CoolerDTO;
import com.pcc.pc_configurator.repositories.CoolerRepository;
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
@RequestMapping("/products/cooler")
@RequiredArgsConstructor
public class CoolerController {
    private final CoolerRepository coolerRepo;
    private List<CoolerDTO> coolerDtoList = new ArrayList<>();

    //@GetMapping
    //public ResponseEntity<Iterable<Cooler>> getAllCoolers() { return ResponseEntity.ok(coolerRepo.findAll());}

    @GetMapping("/{id}")
    public CoolerDTO getOneCpu(@PathVariable int id) {
        return coolerDtoList.get(id);
    }

    @Autowired
    public void cpuToDTO(ModelMapper modelMapper) {
        for(int i=0;i<coolerRepo.findAll().size();++i)
            coolerDtoList.add(modelMapper.map(coolerRepo.findAll().get(i),CoolerDTO.class));
    }

    @GetMapping
    public List<CoolerDTO> getOrders() {
        return coolerDtoList;
    }
}
