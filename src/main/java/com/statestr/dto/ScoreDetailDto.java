package com.statestr.dto;

/**
 * Created by ruantianbo on 2017/4/11.
 */
public class ScoreDetailDto {

    private String label;
    private Integer homeScore;
    private Integer awayScore;
    private String video;

    public ScoreDetailDto() {
    }

    public ScoreDetailDto(String label, Integer homeScore, Integer awayScore, String video) {
        this.label = label;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.video = video;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
