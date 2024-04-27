package com.example.userscrud.rest;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateRequest;
import com.example.userscrud.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService usersService;

    @PutMapping
    public ResponseEntity<UserDto> createOrFullUpdateUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(usersService.createOrFullUpdateUser(userCreateRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(@RequestBody @Valid UserCreateRequest userUpdateRequest, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(usersService.partialUpdateUser(userUpdateRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findUsers(@RequestBody @Valid FindUsersFilter findUsersFilter) {
        return ResponseEntity.ok(usersService.findUsers(findUsersFilter));
    }
}
