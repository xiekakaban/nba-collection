package com.statestr.service;

import com.statestr.entity.TeamEntity;
import com.statestr.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<TeamEntity> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public TeamEntity addTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity);
    }

    @Override
    public TeamEntity findByDetailUrlLike(String likeStr) {
        return teamRepository.findByDetailUrlLike(likeStr);
    }
}
