package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.CartDTO;
import com.pcc.pc_configurator.entities.*;
import com.pcc.pc_configurator.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/getItemList")
    public Map<String, Object> getItemList(@RequestParam String email) {
        Map<String, Object> map = new HashMap<>();
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }

        map.put("products", cartDTOList);
        map.put("totalPrice", cartDTOList.stream().map(o -> o.getProduct().getPrice()* o.getQuantity()).mapToDouble(Float::doubleValue).sum());

        return map;
    }

    @PostMapping("/getItemCount")
    public int getItemCount(@RequestParam String email) {
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }

        return cartDTOList.stream().map(o -> o.getQuantity()).mapToInt(Integer::intValue).sum();
    }

    @PostMapping("/clear")
    public boolean clear(@RequestParam String email) {
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }
        for(int i=0; i<cartDTOList.size(); ++i) {
            cartRepository.deleteById(cartDTOList.get(i).getId());
        }
//jeżeli się uda true jeżeli nie false
        return true;
    }

    @PostMapping("/deleteItem")
    public boolean deleteItem(@RequestParam String email, @RequestParam long productId) {
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }
        try {
            var tempCartItem = cartDTOList.stream()
                    .filter(p -> (p.getProduct().getId() == productId))
                    .findFirst()
                    .get();
            cartRepository.deleteById(tempCartItem.getId());

        } catch (NoSuchElementException e) {
            e.getCause();
        }
        return true;
    }

    public Cart dtoToCart(CartDTO cartDTO) {
        return new Cart(cartDTO.getUser(),cartDTO.getProduct(),cartDTO.getQuantity());
    }

    @PostMapping("/addItem")
    public void addItem(@RequestParam String email, @RequestParam long productId) throws NoSuchElementException{
        try {
            var user = userRepository.findByEmail(email);
            var product = productRepository.findById(productId).get();
            List<CartDTO> cartDTOList = new ArrayList<>();

            for(var cart : cartRepository.findAll()) {
                if(cart.getUser().getEmail().equals(email))
                    cartDTOList.add(modelMapper.map(cart,CartDTO.class));
            }
            try {
                var tempCartDTO = cartDTOList.stream().filter(p -> (p.getProduct().equals(product))).findFirst().get();
                var temp = cartRepository.getOne(tempCartDTO.getId());
                temp.setQuantity(tempCartDTO.getQuantity() + 1);
                cartRepository.save(temp);
            } catch (NoSuchElementException e) {
                cartRepository.save(dtoToCart(new CartDTO(user,product,1)));
            }
        } catch (NoSuchElementException e) {
            e.getCause();
        }
    }


    @PostMapping("/createOrder")
    public Map<String , Object> createOrder(@RequestParam String email) {
        Map<String, Object> map = new HashMap<>();
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }
        map.put("products", cartDTOList);
        map.put("totalPrice", cartDTOList.stream().map(o -> o.getProduct().getPrice()* o.getQuantity()).mapToDouble(Float::doubleValue).sum());

        var newOrder = new Order_(LocalDate.now(), userRepository.findByEmail(email),  "nieopłacone");
        orderRepository.save(newOrder);
        map.put("orderId", newOrder.getId());

        for(var o :cartDTOList) {
            orderListRepository.save(new OrderList(o.getProduct(), o.getQuantity(), newOrder.getId()));
        }



        //for(int i = 0; i<cartDTOList.size(); ++i) {
        //    orderListRepository.save(new OrderList(cartDTOList.get(i).getProduct(), cartDTOList.get(i).getQuantity()));
        //}
        clear(email);

        return map;
    }

    @GetMapping
    public List<CartDTO> all() {
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }
        return cartDTOList;
    }
}
