package com.statestr.service;

import com.statestr.entity.PlayerEntity;
import com.statestr.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;


    @Override
    public List<PlayerEntity> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public List<PlayerEntity> findPlayerByDetailUrl(String detailUrl) {
        return null;
    }

    @Override
    public PlayerEntity getById(String id) {
        return playerRepository.findOne(id);
    }

    @Override
    public PlayerEntity addPlayer(PlayerEntity playerEntity) {
        return playerRepository.save(playerEntity);
    }

    @Override
    public void deletePlayer(PlayerEntity playerEntity) {
        playerRepository.delete(playerEntity);
    }

    @Override
    public void deltePlayerById(String id) {
        playerRepository.delete(id);
    }

    @Override
    public PlayerEntity findByDetailUrlLike(String likeStr) {
        return playerRepository.findByDetailUrlLike(likeStr);
    }
}
