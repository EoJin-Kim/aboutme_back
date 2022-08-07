package com.zeroback.aboutme.entity;

import com.zeroback.aboutme.dto.request.CreateTeamDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;


    private String name;
    private String summary;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<MemberTeam> memberTeam = new ArrayList<MemberTeam>();

    public static Team create(CreateTeamDto createTeamDto) {
        Team team = new Team();
        team.setName(createTeamDto.getTeamName());
        team.setSummary(createTeamDto.getSummary());

        return team;
    }
}
