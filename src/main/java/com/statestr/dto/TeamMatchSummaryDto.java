package com.statestr.dto;

import com.statestr.entity.TeamEntity;

/**
 * Created by e604845 on 4/17/2017.
 * for All match
 */
public class TeamMatchSummaryDto {

    private TeamEntity homeTeam;
    private TeamEntity awayTeam;
    private Integer matchCount;

    public TeamMatchSummaryDto() {
    }

    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    public TeamEntity getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamEntity homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamEntity getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamEntity awayTeam) {
        this.awayTeam = awayTeam;
    }
}
