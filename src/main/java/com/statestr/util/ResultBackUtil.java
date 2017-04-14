package com.statestr.util;

import com.statestr.entity.ResultBack;
import com.statestr.enums.ResultBackCodeEnum;

/**
 * Created by ruantianbo on 2017/4/9.
 */
public class ResultBackUtil {

    public static ResultBack success(Object o){
        return new ResultBack(ResultBackCodeEnum.STATE_OK.getCode(),ResultBackCodeEnum.STATE_OK.getMsg(),o);
    }

    public static ResultBack success(){
        return new ResultBack(ResultBackCodeEnum.STATE_OK.getCode(),ResultBackCodeEnum.STATE_OK.getMsg(),"");
    }

    public static ResultBack error(ResultBackCodeEnum resultBackCodeEnum){
        return new ResultBack(resultBackCodeEnum.getCode(),resultBackCodeEnum.getMsg(),"");
    }

}
