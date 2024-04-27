package com.example.userscrud.service;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateRequest;

import java.util.List;

public interface UserService {

    UserDto createOrFullUpdateUser(UserCreateRequest userCreateRequest);

    UserDto partialUpdateUser(UserCreateRequest userUpdateRequest, Long id);

    void deleteUser(Long id);

    List<UserDto> findUsers(FindUsersFilter findUsersFilter);
}
