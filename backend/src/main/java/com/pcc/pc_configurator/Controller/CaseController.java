package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CaseDTO;
import com.pcc.pc_configurator.repositories.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/case")
@RequiredArgsConstructor
public class CaseController {
    private final CaseRepository caseRepository;
    private List<CaseDTO> caseDtoList= new ArrayList<>();

    //@GetMapping
    //public ResponseEntity<Iterable<Case_>> getAllCases() { return ResponseEntity.ok(caseRepo.findAll());}
    //@GetMapping("/{id}")
    //@JsonView(Views.Normal.class)
    //public CpuDTO getOneCase(@PathVariable int id) {
    //    return caseDtoList.get(id);
    //}


    @Autowired
    public void caseToDTO(ModelMapper modelMapper) {
        for(var case_ : caseRepository.findAll())
            caseDtoList.add(modelMapper.map(case_,CaseDTO.class));
    }

    @GetMapping
    public List<CaseDTO> getCases() {
        return caseDtoList;
    }
}
