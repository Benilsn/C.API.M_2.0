package com.gft.testapi.config.errors;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError extends RuntimeException {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
