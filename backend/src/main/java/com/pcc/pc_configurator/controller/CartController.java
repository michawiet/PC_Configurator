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
        Map<String, Object> map = new HashMap<>();
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            map.put("authFailed", false);
            List<CartDTO> cartDTOList = new ArrayList<>();

            for (var cart : cartRepository.findAll()) {
                if (cart.getUser().getEmail().equals(email))
                    cartDTOList.add(modelMapper.map(cart, CartDTO.class));
            }

            map.put("products", cartDTOList);
            map.put("productCount", getProductCount(cartDTOList));
            map.put("totalPrice", getTotalPrice(cartDTOList));
        } catch (FirebaseAuthException e) {
            map.put("authFailed", true);
        }

        return map;
    }

    @PostMapping("/getItemCount")
    public int getItemCount(@RequestParam String token) {
        int productCount = -1;
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            List<CartDTO> cartDTOList = new ArrayList<>();

            for (var cart : cartRepository.findAll()) {
                if (cart.getUser().getEmail().equals(email))
                    cartDTOList.add(modelMapper.map(cart, CartDTO.class));
            }
            productCount = getProductCount(cartDTOList);
        } catch (FirebaseAuthException e) {

        }

        return productCount;
    }

    @PostMapping("/clear")
    public boolean clear(@RequestParam String token) throws FirebaseAuthException {
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            List<CartDTO> cartDTOList = new ArrayList<>();

            for (var cart : cartRepository.findAll()) {
                if (cart.getUser().getEmail().equals(email))
                    cartDTOList.add(modelMapper.map(cart, CartDTO.class));
            }

            for (int i = 0; i < cartDTOList.size(); ++i) {
                cartRepository.deleteById(cartDTOList.get(i).getId());
            }
        } catch (FirebaseAuthException e) {
            return false;
        }
        //jeżeli się uda true jeżeli nie false
        return true;
    }

    @PostMapping("/deleteItem")
    public Map<String, Object> deleteItem(@RequestParam String token, @RequestParam long productId) throws FirebaseAuthException {
        Map<String, Object> map = new HashMap<>();
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            map.put("authFailed", false);
            List<CartDTO> cartDTOList = new ArrayList<>();

            for(var cart : cartRepository.findAll()) {
                if(cart.getUser().getEmail().equals(email))
                    cartDTOList.add(modelMapper.map(cart,CartDTO.class));
            }

            var tempCartItem = cartDTOList
                    .stream()
                    .filter(p -> (p.getProduct().getId() == productId))
                    .findFirst()
                    .get();
            cartRepository.deleteById(tempCartItem.getId());
            cartDTOList.remove(tempCartItem);

            map.put("products", cartDTOList);
            map.put("totalPrice", getTotalPrice(cartDTOList));
            map.put("productCount", getProductCount(cartDTOList));
        } catch (NoSuchElementException e) {
            e.getCause();
        } catch (FirebaseAuthException e) {
            map.put("authFailed", true);
        }

        return map;
    }

    public Cart dtoToCart(CartDTO cartDTO) {
        return new Cart(cartDTO.getUser(),cartDTO.getProduct(),cartDTO.getQuantity());
    }

    @PostMapping("/addItem")
    public void addItem(@RequestParam String token, @RequestParam long productId) throws FirebaseAuthException {
        try {
            if(productId < 1) throw new NoSuchElementException();
            var email = firebase.verifyTokenAndGetEmail(token);
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
        } catch (FirebaseAuthException e) {

        }
    }

    @PostMapping("/createOrder")
    public Map<String , Object> createOrder(@RequestParam String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            var email = firebase.verifyTokenAndGetEmail(token);
            map.put("authFailed", false);
            List<CartDTO> cartDTOList = new ArrayList<>();

            for (var cart : cartRepository.findAll()) {
                if (cart.getUser().getEmail().equals(email))
                    cartDTOList.add(modelMapper.map(cart, CartDTO.class));
            }

            if (cartDTOList.size() == 0) {
                return null;
            }

            map.put("products", cartDTOList);
            map.put("totalPrice", getTotalPrice(cartDTOList));

            var newOrder = new Order_(LocalDate.now(), userRepository.findByEmail(email), "nieopłacone");
            orderRepository.save(newOrder);
            map.put("orderId", newOrder.getId());

            for (var o : cartDTOList) {
                orderListRepository.save(new OrderList(o.getProduct(), o.getQuantity(), newOrder.getId()));
            }
            clear(token);
        } catch (FirebaseAuthException e) {
            map.put("authFailed", true);
        }

        return map;
    }
}
