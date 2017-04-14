package com.statestr.entity;

import com.statestr.util.GenerateId;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@Entity
@Table(name="nba_player_in_match")
public class PlayerInMatchEntity extends AbstractInMatchEntity{
    @ManyToOne
    @JoinColumn(name="player_id")
    private PlayerEntity player;

    public PlayerInMatchEntity() {
        id = GenerateId.generate();
    }
    @Column(name="is_first")
    @Type(type="yes_no")
    private Boolean isFirst;

    @Column(name="time_on_court",length = 5)
    private Integer timeOnCourt;

    @ManyToOne
    @JoinColumn(name="match_id")
    protected MatchEntity match;

    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Integer getTimeOnCourt() {
        return timeOnCourt;
    }

    public void setTimeOnCourt(Integer timeOnCourt) {
        this.timeOnCourt = timeOnCourt;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
