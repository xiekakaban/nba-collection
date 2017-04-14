package com.statestr.service;

import com.statestr.entity.MatchEntity;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/10.
 */
public interface MatchService {
    public MatchEntity addMatch(MatchEntity matchEntity);
    public MatchEntity findMatchById(String id);
    public List<MatchEntity> findAll();
}
