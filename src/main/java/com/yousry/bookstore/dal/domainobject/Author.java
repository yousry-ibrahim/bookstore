package com.yousry.bookstore.dal.domainobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
/**
 * Author entity class
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
        name = "author",
        uniqueConstraints = @UniqueConstraint(name = "uc_authorName", columnNames = {"name"})
)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "title",
            nullable = true)
    private String title;

    @Column(name = "name",
            nullable = false)
    @NotNull(message = "author name can not be null!")
    private String name;

    @Column(name = "biography",
            nullable = true)
    private String biography;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books;
}
