package com.yousry.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class BookDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String isbn;
    @NotNull
    @NotBlank
    private Long authorId;
    private String authorName;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private BigDecimal price;
    @NotNull
    @NotBlank
    private Long classificationId;
    private String classificationName;
}
