package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.StorageDTO;
import com.pcc.pc_configurator.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/storage")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StorageController {
    private final StorageRepository storageRepository;
    private List<StorageDTO> storageDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void storageToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for(var storage : storageRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                storageDtoList.add(modelMapper.map(storage,StorageDTO.class));
        else if(sortingOrder.equals("desc"))
            for(var storage : storageRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                storageDtoList.add(modelMapper.map(storage,StorageDTO.class));
        else if(sortingOrder.equals(""))
            for(var storage : storageRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                storageDtoList.add(modelMapper.map(storage,StorageDTO.class));
    }

    public void storage(ModelMapper modelMapper) {
        for(var storage : storageRepository.findAll())
            storageDtoList.add(modelMapper.map(storage,StorageDTO.class));
    }

    @GetMapping(params = {"id"})
    public StorageDTO getOneStorage(@RequestParam("id") int id) {
        storageDtoList.clear();
        storage(modelMapper);
        return storageDtoList.get(id);
    }

    @GetMapping
    public List<StorageDTO> getStorages(@RequestParam("page") int page,
                                        @RequestParam("size") int size,
                                        @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                        @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        storageDtoList.clear();
        storageToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return storageDtoList;
    }
}
