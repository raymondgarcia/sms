package com.demo.sms.user;

import com.demo.sms.security.KeycloakService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class UserController {

    private final KeycloakService service;

    @PostMapping("users")
    public ResponseEntity<AppUser> saveUsers(@RequestBody AppUser user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users").toUriString());
        return ResponseEntity.created(uri).body(service.saveUser(user));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AppUser user){
        return ResponseEntity.ok(service.login(user));
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}
