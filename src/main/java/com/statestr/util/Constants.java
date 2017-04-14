package com.statestr.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruantianbo on 2017/3/27.
 */
public class Constants {
    public static final String BASEURL = "http://www.stat-nba.com/";
    public static final String USERAGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    public static final String PLAYERIMAGEPATH = "/Users/ruantianbo/IdeaProjects/nbaCrawl/fetchImage/playerImg/";
    public static final String TEAMIMAGEPATH = "/Users/ruantianbo/IdeaProjects/nbaCrawl/fetchImage/teamImg/";

    public static final String PLAYER_TYPE = "PLAYER";
    public static final String TEAM_TYPE = "TEAM";

    public static final Map<String,String> STORE_DESTINATION = new HashMap<String, String>(){
        {
            put(PLAYER_TYPE,PLAYERIMAGEPATH);
            put(TEAM_TYPE,TEAMIMAGEPATH);
        }
    };



}
