package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CaseDTO;
import com.pcc.pc_configurator.Entities.Case_;
import com.pcc.pc_configurator.Repositories.CaseRepository;
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
@RequestMapping("/products/case")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CaseController {
    private final CaseRepository caseRepository;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping(params = {"id"})
    public CaseDTO getOneCase(@RequestParam("id") int id) {
        return modelMapper.map(caseRepository.findById(Long.valueOf(id)).get(),CaseDTO.class);
    }

    @GetMapping
    public Map<String, Object> getCases(@RequestParam("page") int page,
                                  @RequestParam("size") int size,
                                  @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                  @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<CaseDTO> caseDtoList = new ArrayList<>();
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
        Page<Case_> casePage;

        casePage = caseRepository.findAll(pagingSort);

        for(var case_ : casePage) {
            caseDtoList.add(modelMapper.map(case_,CaseDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", caseDtoList);
        response.put("currentPage", casePage.getNumber());
        response.put("totalItems",casePage.getTotalElements());
        response.put("totalPages", casePage.getTotalPages());

        return response;
    }
}
