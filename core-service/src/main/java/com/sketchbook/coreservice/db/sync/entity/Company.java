package com.sketchbook.coreservice.db.sync.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company", schema = "team_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Company {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
}
