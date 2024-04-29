package com.example.userscrud.service;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.helper.TestHelper;
import com.example.userscrud.mapper.UserMapper;
import com.example.userscrud.repository.UserRepository;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.rest.request.UserPartialUpdateRequest;
import com.example.userscrud.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.userscrud.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl tested;

    private static UserCreateOrUpdateRequest userCreateOrUpdateRequest;

    private static UserEntity entity;

    private static UserDto userDto;

    @BeforeEach
    public void setUp() {
        userCreateOrUpdateRequest = getUserCreateOrUpdateRequest();
        entity = getUserEntity();
        userDto = getUserDto();
    }

    @Test
    public void testCreateOrFullUpdateUser_shouldReturnCreatedUser() {
        when(userMapper.mapUserCreateRequestToUserEntity(userCreateOrUpdateRequest)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);
        when(userMapper.mapToDto(entity)).thenReturn(userDto);

        UserDto actual = tested.createOrFullUpdateUser(userCreateOrUpdateRequest);

        assertEquals(actual, userDto);

        verify(userRepository).findByEmail(userCreateOrUpdateRequest.getEmail());
        verify(userMapper).mapUserCreateRequestToUserEntity(userCreateOrUpdateRequest);
        verify(userRepository).save(entity);
        verify(userMapper).mapToDto(entity);
    }

    @Test
    public void testCreateOrFullUpdateUser_shouldReturnFullUpdatedUser() {
        userCreateOrUpdateRequest.setFirstName("Andrew");
        when(userRepository.findByEmail(userCreateOrUpdateRequest.getEmail())).thenReturn(entity);

        entity.setFirstName("Andrew");
        when(userMapper.mapToEntity(userCreateOrUpdateRequest, entity.getId())).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);

        userDto.setFirstName("Andrew");
        when(userMapper.mapToDto(entity)).thenReturn(userDto);

        UserDto actual = tested.createOrFullUpdateUser(userCreateOrUpdateRequest);

        assertEquals(actual.getFirstName(), entity.getFirstName());

        verify(userRepository).findByEmail(userCreateOrUpdateRequest.getEmail());
        verify(userMapper).mapToEntity(userCreateOrUpdateRequest, entity.getId());
        verify(userRepository).save(entity);
        verify(userMapper).mapToDto(entity);
    }

    @Test
    public void testPartialUpdateUser_shouldReturnUpdatedUser() {
        UserPartialUpdateRequest userPartialUpdateRequest = getUserPartialUpdateRequest();

        UserEntity updatedEntity = getUserEntity();
        updatedEntity.setFirstName(userPartialUpdateRequest.getFirstName());
        updatedEntity.setLastName(userPartialUpdateRequest.getLastName());

        userDto.setFirstName(updatedEntity.getFirstName());
        userDto.setLastName(updatedEntity.getLastName());

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userMapper.updateEntity(userPartialUpdateRequest, entity)).thenReturn(updatedEntity);
        when(userRepository.save(updatedEntity)).thenReturn(updatedEntity);
        when(userMapper.mapToDto(updatedEntity)).thenReturn(userDto);

        UserDto actual = tested.partialUpdateUser(userPartialUpdateRequest, 1L);

        assertEquals(actual, userDto);

        verify(userRepository).findById(1L);
        verify(userMapper).updateEntity(userPartialUpdateRequest, entity);
        verify(userRepository).save(updatedEntity);
        verify(userMapper).mapToDto(updatedEntity);
    }

    @Test
    public void testPartialUpdateUser_shouldThrowUserNotFoundException() {
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> tested.partialUpdateUser(getUserPartialUpdateRequest(),1L), USER_NOT_FOUND_EXCEPTION_MESSAGE);

        assertEquals(USER_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void testDeleteUser_shouldDeleteUser() {
        tested.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    public void testFindUsers_shouldReturnListOfUserDto() {
        FindUsersFilter findUsersFilter = getFindUserFilter();

        when(userRepository.findByBirthDateBetween(findUsersFilter.getFromDate(), findUsersFilter.getToDate())).thenReturn(List.of(entity));
        when(userMapper.mapToDto(List.of(entity))).thenReturn(List.of(userDto));

        List<UserDto> actual = tested.findUsers(findUsersFilter);

        assertEquals(actual, List.of(userDto));

        verify(userRepository).findByBirthDateBetween(findUsersFilter.getFromDate(), findUsersFilter.getToDate());
        verify(userMapper).mapToDto(List.of(entity));
    }

}
