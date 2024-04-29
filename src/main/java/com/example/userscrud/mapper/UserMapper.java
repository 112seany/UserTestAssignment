package com.example.userscrud.mapper;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.rest.request.UserPartialUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", imports = {Objects.class})
public interface UserMapper {

    UserEntity mapUserCreateRequestToUserEntity(UserCreateOrUpdateRequest userCreateOrUpdateRequest);

    UserDto mapToDto(UserEntity entity);

    UserEntity mapToEntity(UserCreateOrUpdateRequest userCreateOrUpdateRequest, Long id);

    List<UserDto> mapToDto(List<UserEntity> userEntities);

    @Mapping(target = "email", expression = "java(Objects.isNull(updateRequest.getEmail()) ? entityToUpdate.getEmail() : updateRequest.getEmail())")
    @Mapping(target = "firstName", expression = "java(Objects.isNull(updateRequest.getFirstName()) ? entityToUpdate.getFirstName() : updateRequest.getFirstName())")
    @Mapping(target = "lastName", expression = "java(Objects.isNull(updateRequest.getLastName()) ? entityToUpdate.getLastName() : updateRequest.getLastName())")
    @Mapping(target = "birthDate", expression = "java(Objects.isNull(updateRequest.getBirthDate()) ? entityToUpdate.getBirthDate() : updateRequest.getBirthDate())" )
    @Mapping(target = "address", expression = "java(Objects.isNull(updateRequest.getAddress()) ? entityToUpdate.getAddress() : updateRequest.getAddress())")
    @Mapping(target = "phoneNumber", expression = "java(Objects.isNull(updateRequest.getPhoneNumber()) ? entityToUpdate.getPhoneNumber() : updateRequest.getPhoneNumber())")
    UserEntity updateEntity(UserPartialUpdateRequest updateRequest, UserEntity entityToUpdate);
}
