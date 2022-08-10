package com.zeroback.aboutme.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Getter
@Setter
public class MemberDetailRequestDto {
    private MultipartFile memberImage;
    private String name;
    private String job;
    private String phone;
    private String content;
    private ArrayList<String> tag;

}
