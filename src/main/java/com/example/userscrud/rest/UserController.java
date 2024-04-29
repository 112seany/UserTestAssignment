package com.example.userscrud.rest;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.rest.request.UserPartialUpdateRequest;
import com.example.userscrud.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity<UserDto> createOrUpdateUser(@RequestBody @Valid UserCreateOrUpdateRequest userCreateOrUpdateRequest) {
        return ResponseEntity.ok(userService.createOrFullUpdateUser(userCreateOrUpdateRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(@RequestBody @Valid UserPartialUpdateRequest userUpdateRequest, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.partialUpdateUser(userUpdateRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findUsers(@RequestBody @Valid FindUsersFilter findUsersFilter) {
        return ResponseEntity.ok(userService.findUsers(findUsersFilter));
    }
}
