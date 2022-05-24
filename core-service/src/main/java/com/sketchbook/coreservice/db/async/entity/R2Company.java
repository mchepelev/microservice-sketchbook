package com.sketchbook.coreservice.db.async.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;


@Entity
@Table("team_management.company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class R2Company {
    @Id
    @javax.persistence.Id
    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
}
