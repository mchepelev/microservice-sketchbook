package com.sketchbook.reactiveservice.example.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ExamplePostResponse {
    private Integer id;
    private String name;
    private Integer number;
    private String text;
}
