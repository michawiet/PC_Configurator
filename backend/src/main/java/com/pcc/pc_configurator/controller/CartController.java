package com.pcc.pc_configurator.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.pcc.pc_configurator.DTO.CartDTO;
import com.pcc.pc_configurator.FirebaseInitializer;
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
    @Autowired
    FirebaseInitializer firebase;

    private int getProductCount(List<CartDTO> cartDTOList) {
        return cartDTOList.stream().map(o -> o.getQuantity()).mapToInt(Integer::intValue).sum();
    }

    private double getTotalPrice(List<CartDTO> cartDTOList) {
        return cartDTOList.stream().map(o -> o.getProduct().getPrice() * o.getQuantity()).mapToDouble(Float::doubleValue).sum();
    }

    @PostMapping("/getItemList")
    public Map<String, Object> getItemList(@RequestParam String token) throws FirebaseAuthException {
        var email = firebase.verifyTokenAndGetEmail(token);
        Map<String, Object> map = new HashMap<>();
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }

        map.put("products", cartDTOList);
        map.put("productCount", getProductCount(cartDTOList));
        map.put("totalPrice", getTotalPrice(cartDTOList));

        return map;
    }

    @PostMapping("/getItemCount")
    public int getItemCount(@RequestParam String token) throws FirebaseAuthException {
        var email = firebase.verifyTokenAndGetEmail(token);
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }

        return getProductCount(cartDTOList);
    }

    @PostMapping("/clear")
    public boolean clear(@RequestParam String token) throws FirebaseAuthException {
        var email = firebase.verifyTokenAndGetEmail(token);
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
    public Map<String, Object> deleteItem(@RequestParam String token, @RequestParam long productId) throws FirebaseAuthException {
        var email = firebase.verifyTokenAndGetEmail(token);
        List<CartDTO> cartDTOList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }
        try {
            var tempCartItem = cartDTOList
                    .stream()
                    .filter(p -> (p.getProduct().getId() == productId))
                    .findFirst()
                    .get();
            cartRepository.deleteById(tempCartItem.getId());
            cartDTOList.remove(tempCartItem);

        } catch (NoSuchElementException e) {
            e.getCause();
        }

        map.put("products", cartDTOList);
        map.put("totalPrice", getTotalPrice(cartDTOList));
        map.put("productCount", getProductCount(cartDTOList));

        return map;
    }

    public Cart dtoToCart(CartDTO cartDTO) {
        return new Cart(cartDTO.getUser(),cartDTO.getProduct(),cartDTO.getQuantity());
    }

    @PostMapping("/addItem")
    public void addItem(@RequestParam String token, @RequestParam long productId) throws FirebaseAuthException {
        var email = firebase.verifyTokenAndGetEmail(token);
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
                cartRepository.save(new Cart(user,product,1));
            }
        } catch (NoSuchElementException e) {
            //e.getCause();
        }
    }

    @PostMapping("/createOrder")
    public Map<String , Object> createOrder(@RequestParam String token) throws FirebaseAuthException {
        var email = firebase.verifyTokenAndGetEmail(token);
        Map<String, Object> map = new HashMap<>();
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(var cart : cartRepository.findAll()) {
            if(cart.getUser().getEmail().equals(email))
                cartDTOList.add(modelMapper.map(cart,CartDTO.class));
        }

        if(cartDTOList.size() == 0) {
            return null;
        }

        map.put("products", cartDTOList);
        map.put("totalPrice", getTotalPrice(cartDTOList));

        var newOrder = new Order_(LocalDate.now(), userRepository.findByEmail(email),  "nieopłacone");
        orderRepository.save(newOrder);
        map.put("orderId", newOrder.getId());

        for(var o :cartDTOList) {
            orderListRepository.save(new OrderList(o.getProduct(), o.getQuantity(), newOrder.getId()));
        }

        clear(token);

        return map;
    }
}
