package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.ProductsDTO;
import com.pcc.pc_configurator.entities.Product;
import com.pcc.pc_configurator.repositories.*;
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
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductsController {
    private final ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(params = {"id"})
    public ProductsDTO getOneProduct(@RequestParam("id") int id) {
        return modelMapper.map(productRepository.findById(Long.valueOf(id)).get(), ProductsDTO.class);
    }

    @GetMapping
    public Map<String, Object> getProducts(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                         @RequestParam(value = "sortingOrder",required = false,defaultValue = "") String sortingOrder) {
        List<ProductsDTO> productsDtoList = new ArrayList<>();
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

        Page<Product> productPage;

        productPage = productRepository.findAll(pagingSort);

        for(var product : productPage) {
            productsDtoList.add(modelMapper.map(product, ProductsDTO.class));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", productsDtoList);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());

        return response;
    }
}