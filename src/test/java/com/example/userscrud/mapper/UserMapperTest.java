package com.example.userscrud.mapper;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.rest.request.UserPartialUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.userscrud.helper.TestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    private final UserMapper tested = Mappers.getMapper(UserMapper.class);

    private static UserCreateOrUpdateRequest userCreateOrUpdateRequest;

    private static UserEntity entity;

    private static UserDto userDto;

    private static UserPartialUpdateRequest updateRequest;

    @BeforeEach
    public void setUp() {
        userCreateOrUpdateRequest = getUserCreateOrUpdateRequest();
        entity = getUserEntity();
        userDto = getUserDto();
        updateRequest = getUserPartialUpdateRequest();
    }


    @Test
    public void mapUserCreateRequestToUserEntity() {
        UserEntity actual = tested.mapUserCreateRequestToUserEntity(userCreateOrUpdateRequest);

        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(entity);
    }

    @Test
    public void mapUserCreateRequestToUserEntity_shouldReturnNullWhenCreationRequestIsNull() {
        UserEntity actual = tested.mapUserCreateRequestToUserEntity(null);

        assertNull(actual);
    }

    @Test
    public void mapToDto() {
        UserDto actual = tested.mapToDto(entity);

        assertEquals(actual, userDto);
    }

    @Test
    public void mapToEntity() {
        UserEntity actual = tested.mapToEntity(userCreateOrUpdateRequest, 1L);

        assertEquals(actual, entity);
    }

    @Test
    public void mapToDtoList() {
        List<UserDto> actual = tested.mapToDto(List.of(entity));

        assertEquals(actual, List.of(userDto));
    }

    @Test
    public void updateEntity() {
        UserEntity actual = tested.updateEntity(updateRequest, entity);

        assertEquals(actual.getFirstName(), updateRequest.getFirstName());
        assertEquals(actual.getLastName(), updateRequest.getLastName());
    }
}
