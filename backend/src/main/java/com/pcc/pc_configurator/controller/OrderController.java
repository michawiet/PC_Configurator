package com.pcc.pc_configurator.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.pcc.pc_configurator.DTO.OrderDTO;
import com.pcc.pc_configurator.DTO.OrderListDTO;
import com.pcc.pc_configurator.FirebaseInitializer;
import com.pcc.pc_configurator.repositories.OrderListRepository;
import com.pcc.pc_configurator.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    FirebaseInitializer firebase;

    @PostMapping("/userOrders")
    public List<Map<String, Object>> getUserOrders(@RequestParam String token) {
        List<Map<String, Object>> objectList = new ArrayList<>();
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            List<OrderDTO> orderDtoList = new ArrayList<>();
            Map<Long, List<OrderListDTO>> tempMap = new HashMap<>();

            for (var order : orderRepository
                    .findAll()
                    .stream()
                    .filter(o -> o.getUser().getEmail().equals(email))
                    .collect(Collectors.toList())) {
                orderDtoList.add(modelMapper.map(order,OrderDTO.class));
            }

            orderDtoList.sort(Comparator.comparingLong(OrderDTO::getId).reversed());

            for(var o : orderDtoList)
                tempMap.put(o.getId(), new ArrayList<>());

            for (var orderList : orderListRepository.findAll()) {
                if(tempMap.containsKey(orderList.getOrderId())) {
                    tempMap.get(orderList.getOrderId()).add(modelMapper.map(orderList, OrderListDTO.class));
                }
            }

            for (var order : orderDtoList) {
                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("date", order.getDate());
                objectMap.put("orderId", order.getId());
                objectMap.put("paymentStatus", order.getStatus());
                objectMap.put("products", tempMap.get(order.getId()));
                objectMap.put("totalPrice", tempMap.get(order.getId()).stream().map(o -> o.getProduct().getPrice() * o.getQuantity()).mapToDouble(Float::doubleValue).sum());
                objectList.add(objectMap);
            }
        } catch (FirebaseAuthException e) {
        }

        return objectList;
    }

    @PostMapping("/userOrder")
    public Map<String, Object> getOrder(@RequestParam String token, @RequestParam int orderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            List<OrderListDTO> orderListDTOList = new ArrayList<>();
            OrderDTO orderDTO;
            try {
                var order = orderRepository.findAll()
                        .stream()
                        .filter(p -> p.getId() == orderId && p.getUser().getEmail().equals(email))
                        .findFirst()
                        .get();
                orderDTO = modelMapper.map(order, OrderDTO.class);

                for (var o : orderListRepository.findAll().stream().filter(o -> o.getOrderId() == orderId).collect(Collectors.toList())) {
                    orderListDTOList.add(modelMapper.map(o, OrderListDTO.class));
                }

                map.put("date", orderDTO.getDate());
                map.put("orderId", orderDTO.getId());
                map.put("paymentStatus", orderDTO.getStatus());
                map.put("products", orderListDTOList);
                map.put("totalPrice", orderListDTOList
                        .stream()
                        .map(o -> o.getProduct().getPrice() * o.getQuantity())
                        .mapToDouble(Float::doubleValue)
                        .sum());
                map.put("productCount", orderListDTOList
                        .stream()
                        .map(o -> o.getQuantity())
                        .mapToInt(Integer::intValue)
                        .sum());

            } catch (NoSuchElementException e) {
            }
            //znaleźć order usera
            //automagiczny kod
        } catch (FirebaseAuthException e) {
            map.put("authFailed", true);
        }

        return map;
    }

    @PostMapping("/confirmOrder")
    public boolean confirmOrder(@RequestParam String token, @RequestParam int orderId) {
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            try {
                var order = orderRepository.findAll()
                        .stream()
                        .filter(o -> o.getId() == orderId && o.getUser().getEmail().equals(email))
                        .findFirst()
                        .get();
                order.setStatus("opłacone");
                orderRepository.save(order);
            } catch (NoSuchElementException e) {
                System.out.println(e.getCause());
            }
        } catch (FirebaseAuthException e) {
            return false;
        }
        return true;
    }

    @PostMapping("/cancelOrder")
    private boolean cancelOrder(@RequestParam String token, @RequestParam int orderId) throws FirebaseAuthException {
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            var order = orderRepository.findAll()
                    .stream()
                    .filter(p -> p.getId() == orderId && p.getUser().getEmail().equals(email))
                    .findFirst()
                    .get();

            for(var o : orderListRepository.findAll().stream().filter(o -> o.getOrderId() == orderId).collect(Collectors.toList())) {
                orderListRepository.delete(o);
            }

            orderRepository.delete(order);
        } catch(NoSuchElementException e) {
            System.out.println(e.getCause());
            return false;
        } catch (FirebaseAuthException e) {
            return false;
        }
        return true;
    }
}
