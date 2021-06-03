package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.PsuDTO;
import com.pcc.pc_configurator.Entities.Psu;
import com.pcc.pc_configurator.Repositories.PsuRepository;
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
@RequestMapping("/products/psu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PsuController {
    private final PsuRepository psuRepository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(params = {"id"})
    public PsuDTO getOnePsu(@RequestParam("id") int id) {
        return modelMapper.map(psuRepository.findById(Long.valueOf(id)).get(),PsuDTO.class);
    }

    @GetMapping
    public Map<String, Object> getPsus(@RequestParam("page") int page,
                                       @RequestParam("size") int size,
                                       @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortingOrder", required = false, defaultValue = "") String sortingOrder) {
        List<PsuDTO> psuDTOList = new ArrayList<>();
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

        Page<Psu> psuPage = psuRepository.findAll(pagingSort);

        for(var psu : psuPage) {
            psuDTOList.add(modelMapper.map(psu, PsuDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", psuDTOList);
        response.put("currentPage", psuPage.getNumber());
        response.put("totalItems", psuPage.getTotalElements());
        response.put("totalPages", psuPage.getTotalPages());

        return response;
    }
}