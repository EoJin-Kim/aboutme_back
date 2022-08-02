package com.zeroback.aboutme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MemberTag {
    @Id
    @GeneratedValue
    @Column(name = "member_tag")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private String tag;

    public static MemberTag create(Member findMember, String tagStr) {
        MemberTag memberTag = new MemberTag();
        memberTag.setTag(tagStr);
        memberTag.setMember(findMember);
        return memberTag;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getMemberTag().add(this);
    }
}
