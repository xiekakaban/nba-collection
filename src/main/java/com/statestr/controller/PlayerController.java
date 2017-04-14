package com.statestr.controller;

import com.statestr.entity.PlayerEntity;
import com.statestr.entity.ResultBack;
import com.statestr.enums.ResultBackCodeEnum;
import com.statestr.repository.PlayerRepository;
import com.statestr.service.PlayerService;
import com.statestr.util.ResultBackUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@RestController
public class PlayerController {
    private Logger logger = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private PlayerService playerService;

    @RequestMapping("/player/get/{id}")
    public ResultBack<PlayerEntity> getPlay(@PathVariable("id") String id){
        logger.debug(""+Thread.currentThread().hashCode());
        PlayerEntity p = playerService.getById(id);
        if(p != null){
            return ResultBackUtil.success(p);
        }else{
            return ResultBackUtil.error(ResultBackCodeEnum.PARAM_ERROR);
        }
    }

    @GetMapping("/play/ex")
    public ResultBack<PlayerEntity> throwException() throws Exception{
        throw new Exception();
    }
}
