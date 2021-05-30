package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.GpuDTO;
import com.pcc.pc_configurator.entities.Gpu;
import com.pcc.pc_configurator.repositories.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/gpu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GpuController {
    private final GpuRepository gpuRepository;
    private List<GpuDTO> gpuDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(params = {"id"})
    public GpuDTO getOneGpu(@RequestParam("id") int id) {
        var gpuRepo = gpuRepository.findById(Long.valueOf(id));
        return modelMapper.map(gpuRepo,GpuDTO.class);
    }

    @GetMapping
    public Map<String, Object> getGpus(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {

        List<GpuDTO> gpuDtoList = new ArrayList<>();
        Pageable pagingSort;
        switch (sortingOrder) {
            case "asc":
                pagingSort = PageRequest.of(page, size, Sort.by(sortBy).ascending());
                break;
            case "desc":
                pagingSort = PageRequest.of(page, size, Sort.by(sortBy).descending());
                break;
            default:
                pagingSort = PageRequest.of(page, size, Sort.by(sortBy));
                break;
        }

        Page<Gpu> gpuPage;

        gpuPage = gpuRepository.findAll(pagingSort);

        for(var gpu : gpuPage) {
            gpuDtoList.add(modelMapper.map(gpu, GpuDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", gpuDtoList);
        response.put("currentPage", gpuPage.getNumber());
        response.put("totalItems",gpuPage.getTotalElements());
        response.put("totalPages", gpuPage.getTotalPages());

        return response;
    }
}