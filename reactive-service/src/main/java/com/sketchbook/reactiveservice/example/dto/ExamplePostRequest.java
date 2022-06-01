package com.sketchbook.reactiveservice.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ExamplePostRequest {
    private String name;
    private Integer number;
    private String text;
}
