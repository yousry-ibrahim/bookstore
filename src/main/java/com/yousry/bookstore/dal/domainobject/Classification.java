package com.yousry.bookstore.dal.domainobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Classification entity class
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
        name = "classification",
        uniqueConstraints = @UniqueConstraint(name = "uc_classificationName", columnNames = {"name"})
)
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classification_sequence")
    @SequenceGenerator(name = "classification_sequence", sequenceName = "classification_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "name",
            nullable = false)
    @NotNull(message = "Classification name can not be null!")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classification")
    private List<Book> books;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    private Promotion promotion;
}
