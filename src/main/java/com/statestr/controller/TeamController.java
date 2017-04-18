package com.statestr.controller;

import com.statestr.dto.AllTeamMatchDto;
import com.statestr.dto.ResultBack;
import com.statestr.entity.MatchEntity;
import com.statestr.entity.TeamEntity;
import com.statestr.repository.TeamRepository;
import com.statestr.service.MatchService;
import com.statestr.service.TeamService;
import com.statestr.util.ResultBackUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e604845 on 4/17/2017.
 */
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @ResponseBody
    @GetMapping("/ajax/team/allmatchSummary")
    public ResultBack<AllTeamMatchDto> ajaxAllmatchSummary(){
        AllTeamMatchDto allTeamMatchDto = new AllTeamMatchDto();
        List<String> teamShortName = new ArrayList<>();
        teamService.findAllTeams().forEach(t -> {
            teamShortName.add(t.getShortNameCh());
        });
        allTeamMatchDto.setTeamNameList(teamShortName);
        for(int i=0;i<teamShortName.size();i++){
            for(int j=0;j<teamShortName.size();j++){
                String homeTeamShortNameCh = teamShortName.get(i);
                String awayTeamShortNameCh = teamShortName.get(j);
                Integer matchCount = 0;
                if(i==j){
                    matchCount = 0;
                }else{
                    matchCount = matchService.getTeamMatchCountByShortNameCh(homeTeamShortNameCh,awayTeamShortNameCh);
                }
                allTeamMatchDto.getTeamMatchCountMap().put(homeTeamShortNameCh+"_"+awayTeamShortNameCh,matchCount);
            }
        }
        return ResultBackUtil.success(allTeamMatchDto);
    }

    @ResponseBody
    @GetMapping("/ajax/team/competionTeam")
    public ResultBack<List<MatchEntity>> ajaxCompectionTeam(String homeTeamShortNameCh,String awayTeamShortNameCh,Boolean ignore){
        List<MatchEntity> matchEntityList = matchService.getTeamMatchByShortNameCh(homeTeamShortNameCh,awayTeamShortNameCh);

        return ResultBackUtil.success(matchEntityList);

    }
}
