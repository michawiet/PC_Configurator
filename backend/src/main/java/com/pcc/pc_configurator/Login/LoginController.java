package com.pcc.pc_configurator.Login;

import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.UserRepository;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/loginSuccess")
    public ResponseEntity<Map> getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                    .getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity <Map>response = restTemplate
                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
            model.addAttribute("name", userAttributes.get("name"));
            model.addAttribute("email", userAttributes.get("email"));
            if(userRepository.findByEmail(model.getAttribute("email").toString())==null) {
            User user = new User(model.getAttribute("email").toString(),model.getAttribute("name").toString(), "");
            userRepository.save(user);
            }
            return response;
        }
        return null;
    }


    //public ResponseEntity<User> getByEmail(@PathVariable long id) {
    //    return userRepository.findById(id)
    //            .map(ResponseEntity::ok)
    //            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    //}

}
