package com.example.userscrud.rest.request;

import com.example.userscrud.rest.validation.DateConstraint;
import com.example.userscrud.rest.validation.DateRangeConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@DateRangeConstraint
public class FindUsersFilter {

    @DateConstraint
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fromDate;

    @DateConstraint
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate toDate;
}
