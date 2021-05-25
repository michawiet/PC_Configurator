package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CaseDTO;
import com.pcc.pc_configurator.repositories.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/case")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CaseController {
    private final CaseRepository caseRepository;
    private List<CaseDTO> caseDtoList= new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void caseToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var case_ : caseRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                caseDtoList.add(modelMapper.map(case_,CaseDTO.class));
        else if(sortingOrder.equals("desc"))
            for(var case_ : caseRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                caseDtoList.add(modelMapper.map(case_,CaseDTO.class));
        else if(sortingOrder.equals(""))
            for(var case_ : caseRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                caseDtoList.add(modelMapper.map(case_,CaseDTO.class));
    }

    public void oneCase(ModelMapper modelMapper) {
        for(var case_ : caseRepository.findAll())
            caseDtoList.add(modelMapper.map(case_,CaseDTO.class));
    }

    @GetMapping(params = {"id"})
    public CaseDTO getOneCase(@RequestParam("id") int id) {
        caseDtoList.clear();
        oneCase(modelMapper);
        return caseDtoList.get(id);
    }

    @GetMapping
    public List<CaseDTO> getCases(@RequestParam("page") int page,
                                  @RequestParam("size") int size,
                                  @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                  @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        caseDtoList.clear();
        caseToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return caseDtoList;
    }
}
