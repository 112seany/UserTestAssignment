package com.example.userscrud.rest.request;

import com.example.userscrud.rest.validation.DateConstraint;
import com.example.userscrud.rest.validation.DateRangeConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DateRangeConstraint
public class FindUsersFilter {

    @DateConstraint
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fromDate;

    @DateConstraint
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate toDate;
}
