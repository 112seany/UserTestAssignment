package com.example.userscrud.rest.request;

import com.example.userscrud.rest.validation.DateConstraint;
import com.example.userscrud.rest.validation.EmailConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @EmailConstraint
    @NotBlank(message = "Email name should not be null")
    private String email;

    @NotBlank(message = "First name should not be null")
    private String firstName;

    @NotBlank(message = "Last name should not be null")
    private String lastName;

    @DateConstraint
    @NotNull(message = "Birth date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

}
