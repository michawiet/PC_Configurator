package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.ProductsDTO;
import com.pcc.pc_configurator.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductsController {
    private final ProductRepository productRepo;
    private List<ProductsDTO> productsDtoList = new ArrayList<>();
    @Autowired
    ModelMapper modelMapper;

    public void productToDTO(ModelMapper modelMapper, int page, int size, String sortBy, String sortingOrder) {
        if(sortingOrder.equals("asc"))
            for (var product : productRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).ascending())))
                productsDtoList.add(modelMapper.map(product, ProductsDTO.class));
        else if(sortingOrder.equals("desc")){
            for (var product : productRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending())))
                productsDtoList.add(modelMapper.map(product, ProductsDTO.class));
        }
        else if(sortingOrder.equals("")){
            for (var product : productRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy))))
                productsDtoList.add(modelMapper.map(product, ProductsDTO.class));
        }
    }

    public void product(ModelMapper modelMapper) {
        for (var product : productRepo.findAll())
            productsDtoList.add(modelMapper.map(product, ProductsDTO.class));
    }

    @GetMapping(params = {"id"})
    public ProductsDTO getOneProduct(@RequestParam("id") int id) {
        productsDtoList.clear();
        product(modelMapper);
        return productsDtoList.get(id);
    }

    @GetMapping
    public List<ProductsDTO> getProducts(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                         @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        productsDtoList.clear();
        productToDTO(modelMapper,page,size,sortBy,sortingOrder);
        return productsDtoList;
    }
}