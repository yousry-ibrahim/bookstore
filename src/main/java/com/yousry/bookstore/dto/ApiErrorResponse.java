package com.yousry.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiErrorResponse {
    private Date timestamp;
    private String status;
    private String code;
    private String message;
}
