package com.sketchbook.reactiveservice.r2dbc.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("team_management.office")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Office {
    @Id
    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
    @Column("company_id")
    private Integer companyId;
    @Column("address_id")
    private Integer addressId;
}
