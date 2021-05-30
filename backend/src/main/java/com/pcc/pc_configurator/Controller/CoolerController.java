package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CoolerDTO;
import com.pcc.pc_configurator.DTO.RamDTO;
import com.pcc.pc_configurator.entities.Cooler;
import com.pcc.pc_configurator.repositories.CoolerRepository;
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
@RequestMapping("/products/cooler")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CoolerController {
    private final CoolerRepository coolerRepopository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(params = {"id"})
    public CoolerDTO getOneCpu(@RequestParam("id") int id) {
        var coolerRepo = coolerRepopository.findById(Long.valueOf(id));
        return modelMapper.map(coolerRepo,CoolerDTO.class);
    }

    @GetMapping
    public Map<String, Object> getCoolers(@RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                      @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                      @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<CoolerDTO> coolerDtoList = new ArrayList<>();
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

        Page<Cooler> coolerPage;

        coolerPage = coolerRepopository.findAll(pagingSort);

        for(var cooler : coolerPage) {
            coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", coolerDtoList);
        response.put("currentPage", coolerPage.getNumber());
        response.put("totalItems",coolerPage.getTotalElements());
        response.put("totalPages", coolerPage.getTotalPages());
        return response;
    }
}
