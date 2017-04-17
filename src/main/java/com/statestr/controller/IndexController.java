package com.statestr.controller;

import com.statestr.entity.TeamEntity;
import com.statestr.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruantianbo on 2017/4/16.
 */
@Controller
public class IndexController extends AbstractController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<TeamEntity> teamEntityList = new ArrayList<>();
        teamEntityList.add(teamService.findByDetailUrlLike("PHO.html"));
        teamEntityList.add(teamService.findByDetailUrlLike("OKC.html"));
        modelAndView.addObject("teamlist",teamEntityList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
