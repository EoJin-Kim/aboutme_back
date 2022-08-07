package com.zeroback.aboutme.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamInfoDto {
    @JsonProperty("group_id")
    private Long groupId;
    @JsonProperty("team_name")
    private String teamName;
    private String summary;
    private Integer count;
}
