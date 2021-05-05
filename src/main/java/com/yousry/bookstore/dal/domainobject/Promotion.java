package com.yousry.bookstore.dal.domainobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
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
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_sequence")
    @SequenceGenerator(name = "promotion_sequence", sequenceName = "promotion_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "percentage",
            nullable = false)
    @NotNull(message = "promotionPercentage can not be null!")
    private BigDecimal percentage;

    @Column(name = "reason",
            nullable = true)
    private String reason;

    @OneToOne(mappedBy = "promotion")
    private Classification classification;
}
