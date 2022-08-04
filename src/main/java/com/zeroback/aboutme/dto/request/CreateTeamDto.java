package com.zeroback.aboutme.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamDto {
    @JsonProperty("member_id")
    private Long memberId;
    @JsonProperty("team_name")
    private String teamName;
}
