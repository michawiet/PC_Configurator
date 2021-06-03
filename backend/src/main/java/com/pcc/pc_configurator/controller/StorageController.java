package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.StorageDTO;
import com.pcc.pc_configurator.Entities.Storage;
import com.pcc.pc_configurator.Repositories.StorageRepository;
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
@RequestMapping("/products/storage")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StorageController {
    private final StorageRepository storageRepository;
    private List<StorageDTO> storageDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(params = {"id"})
    public StorageDTO getOneStorage(@RequestParam("id") int id) {
        return modelMapper.map(storageRepository.findById(Long.valueOf(id)).get(), StorageDTO.class);
    }

    @GetMapping
    public Map<String, Object> getStorages(@RequestParam("page") int page,
                                        @RequestParam("size") int size,
                                        @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                        @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<StorageDTO> storageDtoList = new ArrayList<>();
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

        Page<Storage> storagePage;

        storagePage = storageRepository.findAll(pagingSort);

        for(var storage : storagePage) {
            storageDtoList.add(modelMapper.map(storage, StorageDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", storageDtoList);
        response.put("currentPage", storagePage.getNumber());
        response.put("totalItems",storagePage.getTotalElements());
        response.put("totalPages", storagePage.getTotalPages());

        return response;
    }
}
