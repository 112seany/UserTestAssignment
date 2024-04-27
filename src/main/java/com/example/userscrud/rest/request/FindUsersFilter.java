package com.example.userscrud.rest.request;

import com.example.userscrud.rest.validation.DateConstraint;
import com.example.userscrud.rest.validation.DateRangeConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DateRangeConstraint
public class FindUsersFilter {

    @NotNull(message = "Date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @DateConstraint
    private LocalDate fromDate;

    @NotNull(message = "Date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @DateConstraint
    private LocalDate toDate;
}
