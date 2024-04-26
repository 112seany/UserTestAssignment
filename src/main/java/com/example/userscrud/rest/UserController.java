package com.example.userscrud.rest;

import com.example.userscrud.rest.request.UserCreateRequest;
import com.example.userscrud.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService usersService;

    @PutMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreateRequest createRequest) {
        return ResponseEntity.ok(createRequest);
    }
}
