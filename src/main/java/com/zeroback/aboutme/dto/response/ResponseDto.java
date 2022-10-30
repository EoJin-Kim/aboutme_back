package com.zeroback.aboutme.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {
    private ResponseStatus status;
    private T response;
}
