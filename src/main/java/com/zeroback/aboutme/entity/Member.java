package com.zeroback.aboutme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(
        name="MEMBER",
        uniqueConstraints={
                @UniqueConstraint(
                        name="contstraintEmail",
                        columnNames={"email"}
                )
        }
)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String job;
    private String content;
    private String phone;
    private String email;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTeam> memberTeam = new ArrayList<MemberTeam>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberInfo> memberInfo = new ArrayList<MemberInfo>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTag> memberTag = new ArrayList<MemberTag>();

    public static Member createMember(String name, String email, String password) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);
        return member;
    }
}
