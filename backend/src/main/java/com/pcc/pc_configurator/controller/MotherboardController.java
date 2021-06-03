package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.MotherboardDTO;
import com.pcc.pc_configurator.Entities.Motherboard;
import com.pcc.pc_configurator.Repositories.MotherboardRepository;
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
@RequestMapping("/products/motherboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MotherboardController {
    private final MotherboardRepository motherboardRepository;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping(params = {"id"})
    public MotherboardDTO getOneMotherboard(@RequestParam("id") int id) {
        return modelMapper.map(motherboardRepository.findById(Long.valueOf(id)).get(), MotherboardDTO.class);
    }

    @GetMapping
    public Map<String, Object> getMotherboards(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<MotherboardDTO> motherboardDtoList = new ArrayList<>();
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

        Page<Motherboard> motherboardPage;

        motherboardPage = motherboardRepository.findAll(pagingSort);

        for(var motherboard : motherboardPage) {
            motherboardDtoList.add(modelMapper.map(motherboard, MotherboardDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", motherboardDtoList);
        response.put("currentPage", motherboardPage.getNumber());
        response.put("totalItems", motherboardPage.getTotalElements());
        response.put("totalPages", motherboardPage.getTotalPages());

        return response;
    }
}