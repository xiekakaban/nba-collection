package com.statestr.service;

import com.statestr.entity.TeamEntity;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */
public interface TeamService {
    /**
     * 查询所有都team
     * */
    List<TeamEntity> findAllTeams();

    /**
     * 插入一个team
     * */
    TeamEntity addTeam(TeamEntity teamEntity);

    TeamEntity findByDetailUrlLike(String likeStr);
}
