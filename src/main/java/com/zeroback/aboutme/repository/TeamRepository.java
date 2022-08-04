package com.zeroback.aboutme.repository;

import com.zeroback.aboutme.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface TeamRepository extends JpaRepository<Team,Long>,TeamQueryRepository {
    public Optional<Team> findTeamByName(String name);
}
