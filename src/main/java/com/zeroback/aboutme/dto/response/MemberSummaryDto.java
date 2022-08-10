package com.zeroback.aboutme.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberSummaryDto {
    private Long id;
    private String image;
    private String name;
    private String job;
}
