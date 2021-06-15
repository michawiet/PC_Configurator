package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.OrderListDTO;
import com.pcc.pc_configurator.repositories.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderlist")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderListController {
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;
}
