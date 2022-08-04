package com.zeroback.aboutme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberTeam {
    @Id
    @GeneratedValue
    @Column(name = "member_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    Team team;

    public static MemberTeam create(Member member, Team team) {
        MemberTeam memberTeam = new MemberTeam();
        memberTeam.setMember(member);
        memberTeam.setTeam(team);
        return memberTeam;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getMemberTeam().add(this);
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMemberTeam().add(this);
    }
}
