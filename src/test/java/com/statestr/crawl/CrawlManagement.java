package com.statestr.crawl;

import com.statestr.service.MatchService;
import com.statestr.service.PlayerService;
import com.statestr.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlManagement {
    Logger logger = LoggerFactory.getLogger(CrawlTeam.class);
    @Value("${crawl.team}")
    private Boolean isCrawlTeam;
    @Value("${crawl.player}")
    private Boolean isCrawlPlayer;
    @Value("${crawl.match}")
    private Boolean isCrawlMatch;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;


    @Test
    public void crawlTeam(){
        if(isCrawlTeam){
            CrawlTeam c = new CrawlTeam(teamService);
            c.start();
        }
        if(isCrawlMatch){
            CrawlMatch c = new CrawlMatch(teamService,playerService,matchService);
            c.start();
        }
    }


}
