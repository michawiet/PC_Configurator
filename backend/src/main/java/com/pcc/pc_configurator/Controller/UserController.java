package com.pcc.pc_configurator.Controller;

import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUser() { return ResponseEntity.ok(userRepository.findAll());}
}