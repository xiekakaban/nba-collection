package com.statestr.handler;

import com.statestr.entity.ResultBack;
import com.statestr.enums.ResultBackCodeEnum;
import com.statestr.util.ResultBackUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public ResultBack handler(Exception e){
        logger.error("【系统异常】{}", e);
        return ResultBackUtil.error(ResultBackCodeEnum.UNKNOWN_ERROR);
    }
}
