package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.MotherboardDTO;
import com.pcc.pc_configurator.repositories.MotherboardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/motherboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MotherboardController {
    private final MotherboardRepository motherboardRepository;
    private List<MotherboardDTO> motherboardDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void motherboardToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var motherboard : motherboardRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                motherboardDtoList.add(modelMapper.map(motherboard,MotherboardDTO.class));
        else if(sortingOrder.equals("desc"))
            for(var motherboard : motherboardRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                motherboardDtoList.add(modelMapper.map(motherboard,MotherboardDTO.class));
        else if(sortingOrder.equals(""))
            for(var motherboard : motherboardRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                motherboardDtoList.add(modelMapper.map(motherboard,MotherboardDTO.class));
    }

    public void motherboard(ModelMapper modelMapper) {
        for(var motherboard : motherboardRepository.findAll())
            motherboardDtoList.add(modelMapper.map(motherboard,MotherboardDTO.class));
    }

    @GetMapping(params = {"id"})
    public MotherboardDTO getOneMotherboard(@RequestParam("id") int id) {
        motherboardDtoList.clear();
        motherboard(modelMapper);
        return motherboardDtoList.get(id);
    }

    @GetMapping
    public List<MotherboardDTO> getMotherboards(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        motherboardDtoList.clear();
        motherboardToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return motherboardDtoList;
    }
}