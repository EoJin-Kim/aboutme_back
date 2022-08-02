package com.zeroback.aboutme.entity;

import javax.persistence.*;

@Entity
public class MemberTeam {
    @Id
    @GeneratedValue
    @Column(name = "member_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    Team team;
}
