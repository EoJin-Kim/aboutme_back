package com.zeroback.aboutme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberInfo {

    @Id
    @GeneratedValue
    @Column(name = "member_info")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    Member member;

    private String title;
    private String content;


    public static MemberInfo create(Member member, String title, String content) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setTitle(title);
        memberInfo.setContent(content);
        memberInfo.setMember(member);
        return memberInfo;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getMemberInfo().add(this);
    }
}
