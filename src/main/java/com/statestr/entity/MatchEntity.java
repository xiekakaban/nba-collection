package com.statestr.entity;

import com.statestr.util.GenerateId;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruantianbo on 2017/4/9.
 */

@Entity
@Table(name="nba_match")
public class MatchEntity extends AbstractEntity{

    @Id
    private String id;
    @Column(name="happend_time",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date happendTime;


    @ManyToOne(targetEntity = TeamEntity.class)
    @JoinColumn(name="home_team_id",nullable = false)
    private TeamEntity homeTeam;

    @ManyToOne(targetEntity = TeamEntity.class)
    @JoinColumn(name="away_team_id",nullable = false)
    private TeamEntity awayTeam;

    @Column(name="home_score",length = 10,nullable = false)
    private Integer homeScore;

    @Column(name="away_score",length=10,nullable = false)
    private Integer awayScore;
    /**
     * {[
     *   {homeScore:10,awayScore:20,video:http://www.bad1.com},
     *   {homeScore:94,awayScore:60,video:http://www.good2.com},
     *   ...
     * ]}
     * */
    @Type(type="text")
    @Column(name="score_detail")
    private String scoreDetail;

    @Column(name="detail_url")
    private String detailUrl;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "match")
    private Set<PlayerInMatchEntity> playerInMatchSet = new HashSet<PlayerInMatchEntity>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "match")
    private Set<TeamInMatchEntity> teamInMatchSet = new HashSet<TeamInMatchEntity>();



    @Transient
    private TeamInMatchEntity awayTeamInMatchForDisplay = new TeamInMatchEntity();

    @Transient
    private TeamInMatchEntity homeTeamInMatchForDisplay = new TeamInMatchEntity();


    public MatchEntity() {
        id = GenerateId.generate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getHappendTime() {
        return happendTime;
    }

    public void setHappendTime(Date happendTime) {
        this.happendTime = happendTime;
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

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public String getScoreDetail() {
        return scoreDetail;
    }

    public void setScoreDetail(String scoreDetail) {
        this.scoreDetail = scoreDetail;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Set<PlayerInMatchEntity> getPlayerInMatchSet() {
        return playerInMatchSet;
    }

    public void setPlayerInMatchSet(Set<PlayerInMatchEntity> playerInMatchSet) {
        this.playerInMatchSet = playerInMatchSet;
    }

    public Set<TeamInMatchEntity> getTeamInMatchSet() {
        return teamInMatchSet;
    }

    public void setTeamInMatchSet(Set<TeamInMatchEntity> teamInMatchSet) {
        this.teamInMatchSet = teamInMatchSet;
    }

    public TeamInMatchEntity getAwayTeamInMatchForDisplay() {
        return awayTeamInMatchForDisplay;
    }

    public void setAwayTeamInMatchForDisplay(TeamInMatchEntity awayTeamInMatchForDisplay) {
        this.awayTeamInMatchForDisplay = awayTeamInMatchForDisplay;
    }

    public TeamInMatchEntity getHomeTeamInMatchForDisplay() {
        return homeTeamInMatchForDisplay;
    }

    public void setHomeTeamInMatchForDisplay(TeamInMatchEntity homeTeamInMatchForDisplay) {
        this.homeTeamInMatchForDisplay = homeTeamInMatchForDisplay;
    }

    public boolean addTeamInMatch(TeamInMatchEntity teamInMatchEntity){
        if(teamInMatchEntity != null){
            return this.teamInMatchSet.add(teamInMatchEntity);
        }
        return false;
    }
    public boolean removeTeamInMatch(TeamInMatchEntity teamInMatchEntity){
        if (this.teamInMatchSet.contains(teamInMatchEntity)){
            return this.teamInMatchSet.remove(teamInMatchEntity);
        }
        return false;
    }
}
