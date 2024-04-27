package com.example.userscrud.service;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.rest.request.UserCreateRequest;

public interface UserService {

    UserDto createOrUpdateUser(UserCreateRequest userCreateRequest);
}
