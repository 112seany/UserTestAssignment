package com.example.userscrud.mapper;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity mapUserCreateRequestToUserEntity(UserCreateOrUpdateRequest userCreateOrUpdateRequest);

    UserDto mapToDto(UserEntity entity);

    UserEntity mapToEntity(UserCreateOrUpdateRequest userCreateOrUpdateRequest, Long id);

    List<UserDto> mapToDto(List<UserEntity> userEntities);
}
