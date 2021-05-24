package com.pcc.pc_configurator.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.DTO.ProductsDTO;
import com.pcc.pc_configurator.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepository productRepo;
    private List<ProductsDTO> productsDtoList = new ArrayList<>();

    //@GetMapping
    //public ResponseEntity<Iterable<Product>> getAllProducts() {
    //    return ResponseEntity.ok(productRepo.findAll());
    //}

    @GetMapping("/{id}")
    public ProductsDTO getOneProduct(@PathVariable int id) {
        return productsDtoList.get(id);
    }

    @Autowired
    public void productToDTO(ModelMapper modelMapper) {
        var repo = productRepo.findAll();
        for(int i=0;i<repo.size();++i)
            productsDtoList.add(modelMapper.map(repo.get(i),ProductsDTO.class));
    }

    @GetMapping
    public List<ProductsDTO> getOrders() {
        return productsDtoList;
    }
}
