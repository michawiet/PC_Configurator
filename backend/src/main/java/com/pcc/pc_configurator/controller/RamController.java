package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.RamDTO;
import com.pcc.pc_configurator.Entities.Ram;
import com.pcc.pc_configurator.repositories.RamRepository;
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
@RequestMapping("/products/ram")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RamController {
    private final RamRepository ramRepository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(params = {"id"})
    public RamDTO getOneRam(@RequestParam("id") int id) {
        return modelMapper.map(ramRepository.findById(Long.valueOf(id)).get(),RamDTO.class);
    }

    @GetMapping
    public Map<String, Object> getRams(@RequestParam("page") int page,
                                       @RequestParam("size") int size,
                                       @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<RamDTO> ramDtoList = new ArrayList<>();
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

        Page<Ram> ramPage;

        ramPage = ramRepository.findAll(pagingSort);

        for(var ram : ramPage) {
            ramDtoList.add(modelMapper.map(ram, RamDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", ramDtoList);
        response.put("currentPage", ramPage.getNumber());
        response.put("totalItems",ramPage.getTotalElements());
        response.put("totalPages", ramPage.getTotalPages());

        return response;
    }
}