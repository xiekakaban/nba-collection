package com.statestr.entity;

import com.statestr.util.GenerateId;

import javax.persistence.*;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@Entity
@Table(name="nba_team_in_match")
public class TeamInMatchEntity extends AbstractInMatchEntity{


    public TeamInMatchEntity() {
        id = GenerateId.generate();
    }
    @ManyToOne
    @JoinColumn(name="team_id")
    private TeamEntity teamEntity;
    /**上场人数*/
    @Column(name="player_on_court",length = 5)
    private Integer playerOnCourt;


    @ManyToOne
    @JoinColumn(name = "match_id")
    private MatchEntity match;

    public Integer getPlayerOnCourt() {
        return playerOnCourt;
    }

    public void setPlayerOnCourt(Integer playerOnCourt) {
        this.playerOnCourt = playerOnCourt;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }
}
