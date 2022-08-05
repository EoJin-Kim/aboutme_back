package com.zeroback.aboutme.service;

import com.zeroback.aboutme.dto.request.LoginDto;
import com.zeroback.aboutme.dto.request.MemberDetailRequestDto;
import com.zeroback.aboutme.dto.request.SignupDto;
import com.zeroback.aboutme.dto.response.LoginResultDto;
import com.zeroback.aboutme.dto.response.MemberDetailResponseDto;
import com.zeroback.aboutme.dto.response.MemberInfoDto;
import com.zeroback.aboutme.dto.response.UpdateMemberInfoDto;
import com.zeroback.aboutme.entity.Member;
import com.zeroback.aboutme.entity.MemberInfo;
import com.zeroback.aboutme.entity.MemberTag;
import com.zeroback.aboutme.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public String signup(SignupDto signupDto) {
        try {
            Member createMember = Member.createMember(signupDto.getName(), signupDto.getEmail(), signupDto.getPassword());
            memberRepository.save(createMember);
            return "success";
        } catch (Exception e) {
            return "false";
        }

    }

    public LoginResultDto login(LoginDto loginDto) throws Exception{
        Optional<Member> loginMember = memberRepository.findMemberByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        Member member = loginMember.orElseThrow();
        LoginResultDto loginResultDto = new LoginResultDto();
        loginResultDto.setMemberId(member.getId());
        loginResultDto.setEmail(member.getEmail());

        return loginResultDto;
    }

    public String updateMember(Long memberId, MemberDetailRequestDto memberDetailInfo) {
        try {
            Member findMember = memberRepository.findById(memberId).get();
            memberRepository.deleteMemberTag(memberId);
            findMember.setName(memberDetailInfo.getName());
            findMember.setJob(memberDetailInfo.getJob());
            findMember.setContent(memberDetailInfo.getContent());
            findMember.setPhone(memberDetailInfo.getPhone());

            for (String tagStr : memberDetailInfo.getTag()) {
                MemberTag.create(findMember, tagStr);
            }
            return "success";
        } catch (Exception e) {
            return "false";
        }



    }

    public MemberDetailResponseDto getMemberInfo(Long memberId) throws Exception{
        Member findMember = memberRepository.findById(memberId).get();
        MemberDetailResponseDto memberTotalInfo = new MemberDetailResponseDto();
        memberTotalInfo.setName(findMember.getName());
        String job =  findMember.getJob()!=null ? findMember.getJob() : "";
        memberTotalInfo.setJob(job);
        String content =  findMember.getContent()!=null ? findMember.getContent() : "";
        memberTotalInfo.setContent(content);
        String phone = findMember.getPhone() != null ? findMember.getPhone() : "";
        memberTotalInfo.setPhone(phone);
        memberTotalInfo.setEmail(findMember.getEmail());
        ArrayList<String> tags = new ArrayList<>();

        for (MemberTag memberTag : findMember.getMemberTag()) {
            tags.add(memberTag.getTag());
        }
        memberTotalInfo.setTag(tags);

        ArrayList<MemberInfoDto> memberInfoList = new ArrayList<>();
        for (MemberInfo memberInfo : findMember.getMemberInfo()) {
            memberInfoList.add(new MemberInfoDto(memberInfo.getId(),memberInfo.getTitle(),memberInfo.getContent()));
        }
        memberTotalInfo.setMemberInfo(memberInfoList);
        return memberTotalInfo;
    }

    public String updateMemberInfo(Long memberInfoId, UpdateMemberInfoDto updateMemberInfoDto) {
        MemberInfo memberInfo =memberRepository.findMemberInfoById(memberInfoId);
        memberInfo.setContent(updateMemberInfoDto.getMemberInfo());
        return "success";
    }
}
