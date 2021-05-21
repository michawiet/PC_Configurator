package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.CpuDTO;
import com.pcc.pc_configurator.DTO.GpuDTO;
import com.pcc.pc_configurator.Views;
import com.pcc.pc_configurator.entities.Gpu;
import com.pcc.pc_configurator.repositories.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/gpu")
@RequiredArgsConstructor
public class GpuController {
    private final GpuRepository gpuRepo;

    //@GetMapping
    //public ResponseEntity<Iterable<Gpu>> getAllGpu() {
    //    return ResponseEntity.ok(gpuRepo.findAll());}

    private List<GpuDTO> gpuDtoList = new ArrayList<>();

    @Autowired
    public void gpuToDTO(ModelMapper modelMapper) {
        for(int i=0;i<gpuRepo.findAll().size();++i)
            gpuDtoList.add(modelMapper.map(gpuRepo.findAll().get(i),GpuDTO.class));
    }

    @GetMapping
    @JsonView(Views.Normal.class)
    public List<GpuDTO> getGpusDTo() {
        return gpuDtoList;
    }
}