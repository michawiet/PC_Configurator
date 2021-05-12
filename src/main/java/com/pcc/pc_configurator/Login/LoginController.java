package com.pcc.pc_configurator.Login;

import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

    @GetMapping("/register")
    public void blah(Model model) {
        model.addAttribute("user" , new User());
    }

    @PostMapping("/process_register")
    public void processRegistration(User user) {
        userRepository.save(user);
    }

    //public ResponseEntity<User> getByEmail(@PathVariable long id) {
    //    return userRepository.findById(id)
    //            .map(ResponseEntity::ok)
    //            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    //}

}
