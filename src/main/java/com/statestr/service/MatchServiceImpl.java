package com.statestr.service;

import com.statestr.entity.MatchEntity;
import com.statestr.entity.TeamEntity;
import com.statestr.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@Service("matchService")
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public MatchEntity addMatch(MatchEntity matchEntity) {
        return matchRepository.save(matchEntity);
    }

    @Override
    public MatchEntity findMatchById(String id) {
        return matchRepository.findOne(id);
    }

    @Override
    public List<MatchEntity> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public List<MatchEntity> getTeamsMatch(TeamEntity homeTeam, TeamEntity awayTeam) {
        return matchRepository.getTeamsMatch(homeTeam,awayTeam);
    }

    @Override
    public List<MatchEntity> getTeamsMatchIgnore(TeamEntity firTeam, TeamEntity secTeam) {
        return matchRepository.getTeamsMatchIgnore(firTeam,secTeam);
    }

    @Override
    public Integer getTeamMatchCountByShortNameCh(String homeTeam, String awayTeam) {
        return matchRepository.getTeamMatchCountByShortNameCh(homeTeam, awayTeam);
    }

    @Override
    public List<MatchEntity> getTeamMatchByShortNameCh(String homeTeamShortNameCh, String awayTeamShortNameCh) {
        return matchRepository.getTeamMatchByShortNameCh(homeTeamShortNameCh, awayTeamShortNameCh);
    }
}
