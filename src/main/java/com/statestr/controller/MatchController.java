package com.statestr.controller;

import com.statestr.dto.ResultBack;
import com.statestr.util.ResultBackUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by e604845 on 4/21/2017.
 */
@Controller
public class MatchController extends AbstractController {

    @GetMapping("/ajax/match/getByTimeAndTeam")
    @ResponseBody
    public ResultBack getByTimeAndTeam(String homeTeam,String awayTeam,String year,String day){

        return ResultBackUtil.success();
    }
}
