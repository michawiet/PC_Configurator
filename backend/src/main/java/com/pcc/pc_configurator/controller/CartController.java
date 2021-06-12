package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.CartDTO;
import com.pcc.pc_configurator.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {
    private final CartRepository cartRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<CartDTO> all() {
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }
        return cartDTOList;
    }
}
