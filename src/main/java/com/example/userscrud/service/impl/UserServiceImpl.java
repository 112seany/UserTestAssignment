package com.example.userscrud.service.impl;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.mapper.UserMapper;
import com.example.userscrud.repository.UserRepository;
import com.example.userscrud.rest.request.UserCreateRequest;
import com.example.userscrud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto createOrUpdateUser(UserCreateRequest userCreateRequest) {
        UserEntity entity = userRepository.findByEmail(userCreateRequest.getEmail());

        if (Objects.isNull(entity)) {
            UserEntity entityToSave = userMapper.mapUserCreateRequestToUserEntity(userCreateRequest);
            return userMapper.mapToDto(userRepository.save(entityToSave));
        }

        UserEntity entityToSave = userMapper.mapEntityToUpdate(userCreateRequest, entity.getId());

        return userMapper.mapToDto(userRepository.save(entityToSave));
    }
}

