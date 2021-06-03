package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.DTO.UserDTO;
import com.pcc.pc_configurator.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/testPost",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public String testPost(@RequestParam String email) {
        var userRepo = userRepository.findByEmail(email).getEmail();
        if(email.equals(userRepo)) {
            return "To działa";
        } else {
            return "nie działa";
        }
    }

    @GetMapping
    public List<UserDTO> getAllUser() { return userDtoList;}
}