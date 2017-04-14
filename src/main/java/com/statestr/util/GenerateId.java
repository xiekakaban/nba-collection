package com.statestr.util;

import java.util.UUID;

/**
 * Created by ruantianbo on 2017/4/9.
 */
public class GenerateId {
    public static String generate(){
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey.toString();
    }
    public static void main(String[] args){
        System.out.println(generate());
    }
}
