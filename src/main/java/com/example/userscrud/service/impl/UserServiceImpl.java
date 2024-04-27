package com.example.userscrud.service.impl;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.mapper.UserMapper;
import com.example.userscrud.repository.UserRepository;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto createOrFullUpdateUser(UserCreateOrUpdateRequest userCreateOrUpdateRequest) {
        UserEntity entity = userRepository.findByEmail(userCreateOrUpdateRequest.getEmail());

        if (Objects.isNull(entity)) {
            UserEntity entityToSave = userMapper.mapUserCreateRequestToUserEntity(userCreateOrUpdateRequest);
            return userMapper.mapToDto(userRepository.save(entityToSave));
        }

        UserEntity entityToSave = userMapper.mapToEntity(userCreateOrUpdateRequest, entity.getId());

        return userMapper.mapToDto(userRepository.save(entityToSave));
    }

    @Override
    public UserDto partialUpdateUser(UserCreateOrUpdateRequest userCreateOrUpdateRequest, Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        UserEntity entityToUpdate = userMapper.mapToEntity(userCreateOrUpdateRequest, id);

        return userMapper.mapToDto(userRepository.save(entityToUpdate));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findUsers(FindUsersFilter findUsersFilter) {
        return userMapper.mapToDto(userRepository.findByBirthDateBetween(findUsersFilter.getFromDate(), findUsersFilter.getToDate()));
    }
}

