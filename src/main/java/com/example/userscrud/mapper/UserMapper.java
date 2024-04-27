package com.example.userscrud.mapper;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.rest.request.UserCreateRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity mapUserCreateRequestToUserEntity(UserCreateRequest userCreateRequest);

    UserDto mapToDto(UserEntity entity);

    UserEntity mapEntityToUpdate(UserCreateRequest userCreateRequest, Long id);

    List<UserDto> mapToDtoList(List<UserEntity> userEntities);
}
