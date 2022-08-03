package com.zeroback.aboutme.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberInfoDto {
    private Long id;
    private String title;
    private String content;
}
