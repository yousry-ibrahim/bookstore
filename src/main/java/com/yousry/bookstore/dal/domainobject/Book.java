package com.yousry.bookstore.dal.domainobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Book entity class
 * @author : yousry
 * @version : 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(
        name = "book",
        uniqueConstraints = @UniqueConstraint(name = "uc_bookTitle", columnNames = {"title"})
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "isbn",
            nullable = false)
    @NotNull(message = "ISBN can not be null!")
    private String isbn;

    @Column(name = "title",
            nullable = false)
    @NotNull(message = "title can not be null!")
    private String title;

    @Column(name = "description",
            nullable = false)
    @NotNull(message = "description can not be null!")
    private String description;

    @Column(name = "price",
            nullable = false)
    @NotNull(message = "price can not be null!")
    @Min(value = 0, message = "Price should be positive value.")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private Classification classification;
}
