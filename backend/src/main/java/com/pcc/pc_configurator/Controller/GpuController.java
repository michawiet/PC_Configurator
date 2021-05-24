package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.GpuDTO;
import com.pcc.pc_configurator.repositories.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/gpu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GpuController {
    private final GpuRepository gpuRepo;
    private List<GpuDTO> gpuDtoList = new ArrayList<>();

    //@GetMapping
    //public ResponseEntity<Iterable<Gpu>> getAllGpu() {
    //    return ResponseEntity.ok(gpuRepo.findAll());}


    @Autowired
    public void gpuToDTO(ModelMapper modelMapper) {
        for(var gpu : gpuRepo.findAll())
            gpuDtoList.add(modelMapper.map(gpu,GpuDTO.class));
    }

    @GetMapping
    public List<GpuDTO> getGpus() {
        return gpuDtoList;
    }
}