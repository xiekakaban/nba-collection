package com.statestr.service;

import com.statestr.entity.MatchEntity;
import com.statestr.entity.TeamEntity;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/10.
 */
public interface MatchService {
    public MatchEntity addMatch(MatchEntity matchEntity);
    public MatchEntity findMatchById(String id);
    public List<MatchEntity> findAll();
    public List<MatchEntity> getTeamsMatch(TeamEntity homeTeam,TeamEntity awayTeam);
    public List<MatchEntity> getTeamsMatchIgnore(TeamEntity firTeam,TeamEntity secTeam);

    public Integer getTeamMatchCountByShortNameCh(String homeTeam,String awayTeam);
    public List<MatchEntity> getTeamMatchByShortNameCh(String homeTeamShortNameCh,String awayTeamShortNameCh);
}
