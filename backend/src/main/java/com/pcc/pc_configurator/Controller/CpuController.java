package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CpuDTO;
import com.pcc.pc_configurator.repositories.CpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products/cpu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CpuController {
    private final CpuRepository cpuRepository;
    private List<CpuDTO> cpuDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void cpuToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var cpu : cpuRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                cpuDtoList.add(modelMapper.map(cpu,CpuDTO.class));
        else if(sortingOrder.equals("desc"))
            for(var cpu : cpuRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                cpuDtoList.add(modelMapper.map(cpu,CpuDTO.class));
        else if(sortingOrder.equals(""))
            for(var cpu : cpuRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))))
                cpuDtoList.add(modelMapper.map(cpu,CpuDTO.class));
    }

    public void cpu(ModelMapper modelMapper) {
        for (var cpu : cpuRepository.findAll())
            cpuDtoList.add(modelMapper.map(cpu, CpuDTO.class));
    }

    @GetMapping(params = {"id"})
    public CpuDTO getOneCpu(@RequestParam("id") int id) {
        cpuDtoList.clear();
        cpu(modelMapper);
        return cpuDtoList.get(id);
    }

    @GetMapping
    public List<CpuDTO> getCpus(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        cpuDtoList.clear();
        cpuToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return cpuDtoList;
    }
}