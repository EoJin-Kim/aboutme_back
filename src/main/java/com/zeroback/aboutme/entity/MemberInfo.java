package com.zeroback.aboutme.entity;

import javax.persistence.*;

@Entity
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


}
