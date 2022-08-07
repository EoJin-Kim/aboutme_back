package com.zeroback.aboutme.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamTotalInfo {
    String groupName;
    String groupSummary;
    Integer count;
    List<MemberSummaryDto> memberSummary;
}
