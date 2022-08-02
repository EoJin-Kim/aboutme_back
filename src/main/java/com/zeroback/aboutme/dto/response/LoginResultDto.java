package com.zeroback.aboutme.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResultDto {
    @JsonProperty("member_id")
    Long memberId;
    String email;
}
