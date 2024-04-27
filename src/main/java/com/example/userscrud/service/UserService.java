package com.example.userscrud.service;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;

import java.util.List;

public interface UserService {

    UserDto createOrFullUpdateUser(UserCreateOrUpdateRequest userCreateOrUpdateRequest);

    UserDto partialUpdateUser(UserCreateOrUpdateRequest userUpdateRequest, Long id);

    void deleteUser(Long id);

    List<UserDto> findUsers(FindUsersFilter findUsersFilter);
}
