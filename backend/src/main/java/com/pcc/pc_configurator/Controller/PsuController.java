package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.PsuDTO;
import com.pcc.pc_configurator.repositories.PsuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/psu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PsuController {
    private final PsuRepository psuRepository;
    private List<PsuDTO> psuDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void psuToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var psu : psuRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                psuDtoList.add(modelMapper.map(psu,PsuDTO.class));
        else if(sortingOrder.equals("desc")) {
            for(var psu : psuRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                psuDtoList.add(modelMapper.map(psu,PsuDTO.class));
        }
        else if(sortingOrder.equals("")) {
            for(var psu : psuRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                psuDtoList.add(modelMapper.map(psu,PsuDTO.class));
        }
    }

    public void psu(ModelMapper modelMapper) {
        for(var psu : psuRepository.findAll())
            psuDtoList.add(modelMapper.map(psu,PsuDTO.class));
    }

    @GetMapping(params = {"id"})
    public PsuDTO getOnePsu(@RequestParam("id") int id) {
        psuDtoList.clear();
        psu(modelMapper);
        return psuDtoList.get(id);
    }

    @GetMapping
    public List<PsuDTO> getPsus(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        psuDtoList.clear();
        psuToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return psuDtoList;
    }
}