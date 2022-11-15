package com.zeroback.aboutme.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamDto {
    @JsonProperty("member_id")
    private Long memberId;
    @JsonProperty("team_name")
    private String teamName;
    private String summary;
    private String password;
}
