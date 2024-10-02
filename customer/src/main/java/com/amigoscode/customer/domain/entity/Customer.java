package com.amigoscode.customer.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("customer")
public class Customer {

    @Id // Marks this field as the primary key
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;

}
