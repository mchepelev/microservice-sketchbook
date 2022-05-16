package com.sketchbook.coreservice.sync;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SlowResponse {
    LocalDateTime timestamp;
    String status;

    public static SlowResponse empty() {
        return new Empty();
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Empty extends SlowResponse {
        public Empty() {
            super(LocalDateTime.now(), "Empty");
        }
    }
}
