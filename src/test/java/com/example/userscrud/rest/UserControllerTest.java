package com.example.userscrud.rest;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.rest.request.UserPartialUpdateRequest;
import com.example.userscrud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static com.example.userscrud.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private static UserCreateOrUpdateRequest userCreateOrUpdateRequest;

    private static UserDto userDto;

    private static UserPartialUpdateRequest updateRequest;

    @BeforeEach
    public void setUp() {
        userCreateOrUpdateRequest = getUserCreateOrUpdateRequest();
        userDto = getUserDto();
        updateRequest = getUserPartialUpdateRequest();
    }

    @Test
    public void TestCreateOrUpdateUser_shouldReturnUserDto() {
        when(userService.createOrFullUpdateUser(userCreateOrUpdateRequest)).thenReturn(userDto);

        ResponseEntity<UserDto> actual = userController.createOrUpdateUser(userCreateOrUpdateRequest);

        assertEquals(actual, ResponseEntity.ok(userDto));
    }

    @Test
    public void TestPartialUpdateUser_shouldReturnUpdatedUserDto() {
        userDto.setFirstName(updateRequest.getFirstName());
        userDto.setLastName(updateRequest.getLastName());

        when(userService.partialUpdateUser(updateRequest, 1L)).thenReturn(userDto);

        ResponseEntity<UserDto> actual = userController.partialUpdateUser(updateRequest, 1L);

        assertEquals(actual.getBody().getFirstName(), updateRequest.getFirstName());
        assertEquals(actual.getBody().getLastName(), updateRequest.getLastName());
    }

    @Test
    public void TestDeleteUser_shouldDeleteUser() {
        userController.deleteUser(1L);

        verify(userService).deleteUser(1L);
    }

    @Test
    public void TestFindUsers_shouldReturnListUserDto() {
        FindUsersFilter filter = new FindUsersFilter(LocalDate.parse("1998-01-01"), LocalDate.parse("2003-05-04"));
        when(userService.findUsers(filter)).thenReturn(List.of(userDto));

        ResponseEntity<List<UserDto>> actual = userController.findUsers(filter);

        assertEquals(actual.getBody(), List.of(userDto));
    }
}
