package com.example.userscrud.service.impl;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.mapper.UserMapper;
import com.example.userscrud.repository.UserRepository;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateRequest;
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
    public UserDto createOrFullUpdateUser(UserCreateRequest userCreateRequest) {
        UserEntity entity = userRepository.findByEmail(userCreateRequest.getEmail());

        if (Objects.isNull(entity)) {
            UserEntity entityToSave = userMapper.mapUserCreateRequestToUserEntity(userCreateRequest);
            return userMapper.mapToDto(userRepository.save(entityToSave));
        }

        UserEntity entityToSave = userMapper.mapEntityToUpdate(userCreateRequest, entity.getId());

        return userMapper.mapToDto(userRepository.save(entityToSave));
    }

    @Override
    public UserDto partialUpdateUser(UserCreateRequest userCreateOrUpdateRequest, Long id) {
        UserEntity userEntity = checkIfUserExists(id);

        UserEntity entityToUpdate = userMapper.mapEntityToUpdate(userCreateOrUpdateRequest, id);

        return userMapper.mapToDto(userRepository.save(entityToUpdate));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findUsers(FindUsersFilter findUsersFilter) {
        return userMapper.mapToDtoList(userRepository.findByBirthDateBetween(findUsersFilter.getFromDate(), findUsersFilter.getToDate()));
    }

    private UserEntity checkIfUserExists(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    return new UserNotFoundException(id);
                });
    }
}

