package com.statestr.service;

import com.statestr.entity.PlayerEntity;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */
public interface PlayerService {
    /**
     * 查找所有都player
     * */
    public List<PlayerEntity> findAllPlayers();

    /**
     * 通过detailUrl查找player
     * */
    public List<PlayerEntity> findPlayerByDetailUrl(String detailUrl);

    /**
     * 通过id 查找 player
     * */
    public PlayerEntity getById(String id);

    /**
     * 添加一个player
     * */
    public PlayerEntity addPlayer(PlayerEntity playerEntity);

    /**
     * 删除一个player
     * */
    public void deletePlayer(PlayerEntity playerEntity);

    /**
     * 通过id 删除一个player
     * */
    public void deltePlayerById(String id);

    public PlayerEntity findByDetailUrlLike(String likeStr);

}
