package com.statestr.dto;

import com.statestr.entity.TeamEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by e604845 on 4/17/2017.
 */
public class AllTeamMatchDto {
    private List<TeamEntity> teamEntityList = new ArrayList<>();
    private Map<String,TeamMatchSummaryDto> teamMatchSummaryList = new HashMap<>();

    public AllTeamMatchDto() {
    }

    public List<TeamEntity> getTeamEntityList() {
        return teamEntityList;
    }

    public void setTeamEntityList(List<TeamEntity> teamEntityList) {
        this.teamEntityList = teamEntityList;
    }

    public Map<String, TeamMatchSummaryDto> getTeamMatchSummaryList() {
        return teamMatchSummaryList;
    }

    public void setTeamMatchSummaryList(Map<String, TeamMatchSummaryDto> teamMatchSummaryList) {
        this.teamMatchSummaryList = teamMatchSummaryList;
    }
}
