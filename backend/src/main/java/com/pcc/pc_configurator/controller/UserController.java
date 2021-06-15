package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.UserDTO;
import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserRepository userRepository;

    List<UserDTO> userDtoList = new ArrayList<>();

    @Autowired
    public void userToDto(ModelMapper modelMapper) {
        for(var user : userRepository.findAll())
            userDtoList.add(modelMapper.map(user,UserDTO.class));
    }

    public User dtoToUser(UserDTO userDTO) {
        return new User(userDTO.getEmail());
    }

    @GetMapping("/register")
    public void updateDB(@RequestParam String email) {
        if(userDtoList
                .stream()
                .filter(p -> (p.getEmail().equals(email)))
                .findFirst()
                .orElse(null) == null) {
            var user = new UserDTO(email);
            System.out.println("email " + user);
            userRepository.save(dtoToUser(user));
        }
    }

    @GetMapping
    public List<UserDTO> getAllUser() { return userDtoList;}
}