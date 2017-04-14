package com.statestr.util;

import java.util.Set;

/**
 * Created by ruantianbo on 2017/3/28.
 */
@SuppressWarnings("Since15")
public class StringUtils {

    public static boolean notNullOrEmpty(String... strs){
        for(String str : strs){
            if(str == null || str.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static boolean in(String str, String... include){
        if(!notNullOrEmpty(str)){
            return false;
        }
        for(String s : include){
            if(s.equals(include)){
                return true;
            }
        }
        return false;
    }
    public static double percentStr2double(String percentStr){
        if(StringUtils.notNullOrEmpty(percentStr)) {
            return (Double.parseDouble(percentStr.substring(0, percentStr.indexOf("%"))) / 100);
        }else{
            return 0.0;
        }
    }
    public static Integer intStr2int(String intStr){
        if(StringUtils.notNullOrEmpty(intStr)) {
            return (Integer.parseInt(intStr));
        }else{
            return 0;
        }
    }


}
