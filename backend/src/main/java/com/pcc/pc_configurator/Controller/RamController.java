package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.RamDTO;
import com.pcc.pc_configurator.repositories.RamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/ram")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RamController {
    private final RamRepository ramRepository;
    private List<RamDTO> ramDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void ramToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var ram : ramRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                ramDtoList.add(modelMapper.map(ram,RamDTO.class));
        else if(sortingOrder.equals("desc")) {
            for(var ram : ramRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                ramDtoList.add(modelMapper.map(ram,RamDTO.class));
        }
        else if(sortingOrder.equals("")) {
            for(var ram : ramRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                ramDtoList.add(modelMapper.map(ram,RamDTO.class));
        }
    }

    public void ram(ModelMapper modelMapper) {
        for(var ram : ramRepository.findAll())
            ramDtoList.add(modelMapper.map(ram,RamDTO.class));
    }

    @GetMapping(params = {"id"})
    public RamDTO getOneRam(@RequestParam("id") int id) {
        ramDtoList.clear();
        ram(modelMapper);
        return ramDtoList.get(id);
    }

    @GetMapping
    public List<RamDTO> getRams(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        ramDtoList.clear();
        ramToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return ramDtoList;
    }
}