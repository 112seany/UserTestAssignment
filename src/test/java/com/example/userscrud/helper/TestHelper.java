package com.example.userscrud.helper;

import com.example.userscrud.dto.UserDto;
import com.example.userscrud.entity.UserEntity;
import com.example.userscrud.rest.request.FindUsersFilter;
import com.example.userscrud.rest.request.UserCreateOrUpdateRequest;
import com.example.userscrud.rest.request.UserPartialUpdateRequest;

import java.time.LocalDate;

public class TestHelper {

    private static final String EMAIL = "dmytro.yrich@gmail.com";
    private static final String FIRST_NAME = "Dmytro";
    private static final String LAST_NAME = "Yrich";
    private static final LocalDate BIRTH_DATE = LocalDate.parse("1999-06-22");
    private static final String ADDRESS = "123 Main St, City";
    private static final String PHONE_NUMBER = "0953043243";
    private static final Long ID = 1L;

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User with id 1 was not found";

    public static UserEntity getUserEntity() {
        return UserEntity.builder()
                .id(ID)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(BIRTH_DATE)
                .address(ADDRESS)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static UserCreateOrUpdateRequest getUserCreateOrUpdateRequest() {
        return UserCreateOrUpdateRequest.builder()
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(BIRTH_DATE)
                .address(ADDRESS)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .id(ID)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(BIRTH_DATE)
                .address(ADDRESS)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static UserPartialUpdateRequest getUserPartialUpdateRequest() {
        return UserPartialUpdateRequest.builder()
                .firstName("David")
                .lastName("Carrasco")
                .build();
    }
    public static FindUsersFilter getFindUserFilter(){
        return FindUsersFilter.builder()
                .fromDate(LocalDate.parse("1999-01-01"))
                .toDate(LocalDate.parse("2003-05-26"))
                .build();
    }
}
