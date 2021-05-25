package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.GpuDTO;
import com.pcc.pc_configurator.repositories.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/gpu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GpuController {
    private final GpuRepository gpuRepo;
    private List<GpuDTO> gpuDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void gpuToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var gpu : gpuRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                gpuDtoList.add(modelMapper.map(gpu,GpuDTO.class));
        else if(sortingOrder.equals("desc"))
            for(var gpu : gpuRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                gpuDtoList.add(modelMapper.map(gpu,GpuDTO.class));
        else if(sortingOrder.equals(""))
            for(var gpu : gpuRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                gpuDtoList.add(modelMapper.map(gpu,GpuDTO.class));
    }

    public void gpu(ModelMapper modelMapper) {
        for(var gpu : gpuRepo.findAll())
            gpuDtoList.add(modelMapper.map(gpu,GpuDTO.class));
    }

    @GetMapping(params = {"id"})
    public GpuDTO getOneGpu(@RequestParam("id") int id) {
        gpuDtoList.clear();
        gpu(modelMapper);
        return gpuDtoList.get(id);
    }

    @GetMapping
    public List<GpuDTO> getGpus(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        gpuDtoList.clear();
        gpuToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return gpuDtoList;
    }
}