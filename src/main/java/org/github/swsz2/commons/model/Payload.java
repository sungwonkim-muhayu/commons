package org.github.swsz2.commons.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Payload<T> {

    @Builder.Default
    private String message = "success";
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private final int code = 0;
    private T data;

    public static <T> Payload<T> ok(T data) {
        return Payload.<T>builder()
                .status(HttpStatus.OK.value())
                .data(data)
                .build();
    }
}
