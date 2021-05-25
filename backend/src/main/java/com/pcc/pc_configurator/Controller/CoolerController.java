package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.CoolerDTO;
import com.pcc.pc_configurator.repositories.CoolerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/cooler")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CoolerController {
    private final CoolerRepository coolerRepo;
    private List<CoolerDTO> coolerDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void coolerToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var cooler : coolerRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class));
        else if(sortingOrder.equals("desc"))
            for(var cooler : coolerRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class));
        else if(sortingOrder.equals(""))
            for(var cooler : coolerRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class));
    }

    public void cooler(ModelMapper modelMapper) {
        for(var cooler : coolerRepo.findAll())
            coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class));
    }

    @GetMapping(params = {"id"})
    public CoolerDTO getOneCpu(@RequestParam("id") int id) {
        coolerDtoList.clear();
        cooler(modelMapper);
        return coolerDtoList.get(id);
    }

    @GetMapping
    public List<CoolerDTO> getCoolers(@RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                      @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                      @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        coolerDtoList.clear();
        coolerToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return coolerDtoList;
    }
}
