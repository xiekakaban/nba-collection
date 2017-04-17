package com.statestr.service.test;

import com.alibaba.fastjson.JSON;
import com.statestr.entity.MatchEntity;
import com.statestr.entity.TeamEntity;
import com.statestr.filter.FastJsonLazyFIilter;
import com.statestr.service.MatchService;
import com.statestr.service.TeamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/17.
 */
public class MatchServiceOOCTest extends BaseOOCTest {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Test
    public void testGetTeamsMatch(){
        TeamEntity milTeam = teamService.findByDetailUrlLike("MIL.html");
        TeamEntity lacTeam = teamService.findByDetailUrlLike("CHA.html");
        List<MatchEntity> matchEntityList = matchService.getTeamsMatch(milTeam,lacTeam);
        System.out.println(JSON.toJSONString(matchEntityList,new FastJsonLazyFIilter()));
    }

    @Test
    public void testGetTeamsMatchIgnore(){
        TeamEntity dalTeam = teamService.findByDetailUrlLike("DAL.html");
        TeamEntity phoTeam = teamService.findByDetailUrlLike("PHO.html");
        List<MatchEntity> matchEntities = matchService.getTeamsMatchIgnore(dalTeam,phoTeam);
        System.out.println(JSON.toJSONString(matchEntities,new FastJsonLazyFIilter()));
    }
}
