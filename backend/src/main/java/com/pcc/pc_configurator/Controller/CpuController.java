package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CpuDTO;
import com.pcc.pc_configurator.entities.Cpu;
import com.pcc.pc_configurator.repositories.CpuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(params = {"id"})
    public CpuDTO getOneCpu(@RequestParam("id") int id) {
        var cpuRepo = cpuRepository.findById(Long.valueOf(id));
        return modelMapper.map(cpuRepo,CpuDTO.class);
    }

    @GetMapping
    public Map<String, Object> getCpus(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<CpuDTO> cpuDtoList = new ArrayList<>();
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

        Page<Cpu> cpuPage;

        cpuPage = cpuRepository.findAll(pagingSort);

        for(var cpu : cpuPage) {
            cpuDtoList.add(modelMapper.map(cpu, CpuDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", cpuDtoList);
        response.put("currentPage", cpuPage.getNumber());
        response.put("totalItems",cpuPage.getTotalElements());
        response.put("totalPages", cpuPage.getTotalPages());

        return response;
    }
}