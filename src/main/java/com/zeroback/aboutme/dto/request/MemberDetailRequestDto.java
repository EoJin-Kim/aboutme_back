package com.zeroback.aboutme.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class MemberDetailRequestDto {
    private String name;
    private String job;
    private String phone;
    private String content;
    private ArrayList<String> tag;

}
