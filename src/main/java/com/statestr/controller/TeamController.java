package com.statestr.controller;

import com.statestr.dto.TeamMatchSummaryDto;
import com.statestr.entity.TeamEntity;
import com.statestr.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by e604845 on 4/17/2017.
 */
@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @ResponseBody
    @GetMapping("/team/allmatch")
    public List<TeamMatchSummaryDto> allTeamMatch(){


    }
}
