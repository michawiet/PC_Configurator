package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.GpuDTO;
import com.pcc.pc_configurator.repositories.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        var repo = gpuRepo.findAll();
        for(int i=0;i<repo.size();++i)
            gpuDtoList.add(modelMapper.map(repo.get(i),GpuDTO.class));
    }

    @GetMapping
    public List<GpuDTO> getGpusDTo() {
        return gpuDtoList;
    }
}