package com.zeroback.aboutme.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class MemberDetailResponseDto {
    private String image;
    private String name;
    private String job;
    private String content;
    private String phone;
    private String email;
    private ArrayList<String> tag;
    private ArrayList<MemberInfoDto> memberInfo;

}
