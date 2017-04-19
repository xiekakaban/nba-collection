package com.statestr.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by e604845 on 4/19/2017.
 */
public class TeamMatchSummaryDto {
    private String homeTeamShortNameCh;
    private String awayTeamShortNameCh;
    private Integer homeTeamScore;
    private Integer awayTeamScore;

    private Integer diffScore;
    private String matchId;
    private Date happendTime;


    public TeamMatchSummaryDto() {
    }

    public String getHomeTeamShortNameCh() {
        return homeTeamShortNameCh;
    }

    public void setHomeTeamShortNameCh(String homeTeamShortNameCh) {
        this.homeTeamShortNameCh = homeTeamShortNameCh;
    }

    public String getAwayTeamShortNameCh() {
        return awayTeamShortNameCh;
    }

    public void setAwayTeamShortNameCh(String awayTeamShortNameCh) {
        this.awayTeamShortNameCh = awayTeamShortNameCh;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Integer getDiffScore() {
        return diffScore;
    }

    public void setDiffScore(Integer diffScore) {
        this.diffScore = diffScore;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Date getHappendTime() {
        return happendTime;
    }

    public void setHappendTime(Date happendTime) {
        this.happendTime = happendTime;
    }
}
