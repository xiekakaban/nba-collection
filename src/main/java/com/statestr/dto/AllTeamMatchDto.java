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
    private List<String> teamNameList = new ArrayList<>();
    private Map<String,Integer> teamMatchCountMap = new HashMap<>();

    public AllTeamMatchDto() {
    }

    public List<String> getTeamNameList() {
        return teamNameList;
    }

    public void setTeamNameList(List<String> teamNameList) {
        this.teamNameList = teamNameList;
    }

    public Map<String, Integer> getTeamMatchCountMap() {
        return teamMatchCountMap;
    }

    public void setTeamMatchCountMap(Map<String, Integer> teamMatchCountMap) {
        this.teamMatchCountMap = teamMatchCountMap;
    }
}
