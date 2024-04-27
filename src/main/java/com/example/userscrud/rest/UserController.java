package com.example.userscrud.rest;

import com.example.userscrud.dto.UserDto;
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
    public ResponseEntity<UserDto> createOrUpdateUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(usersService.createOrUpdateUser(userCreateRequest));
    }
}
