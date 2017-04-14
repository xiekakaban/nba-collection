package com.statestr.crawl;

import com.statestr.entity.AbstractEntity;
import com.statestr.entity.TeamEntity;
import com.statestr.service.TeamService;
import com.statestr.util.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */

public class CrawlTeam extends AbstractCrawl{


    private TeamService teamService;
    public CrawlTeam(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    protected CallableResultBack fetchDetail(String url) {
        CallableResultBack callableResultBack = new CallableResultBack();
        try {
            Document document = Jsoup.connect(url).userAgent(Constants.USERAGENT).get();
            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setDetailUrl(url);
            Element infoPanel = document.select("div.intro").first();
            Element imgEle = infoPanel.select("a.team>div>img").first();
            teamEntity.setLogo(imgEle.attr("src").substring(imgEle.attr("src").lastIndexOf("/")+1));
            teamEntity.setShortName(teamEntity.getLogo().substring(0,teamEntity.getLogo().lastIndexOf(".")));
            teamEntity.setLogoStore("http://www.stat-nba.com/image/teamImage/"+teamEntity.getLogo());
            Element textEle = infoPanel.select(".text").last();
            teamEntity.setNameCh(textEle.select(".head").first().ownText().trim());
            Element englishNameEle = textEle.select(".column:containsOwn(英文名　:)").first();
            teamEntity.setNameEn(englishNameEle != null? englishNameEle.nextElementSibling().ownText():"");
            Element chimpionEle = textEle.select(".column:containsOwn(夺冠赛季:)").first();
            teamEntity.setChimpion(chimpionEle != null ? chimpionEle.nextElementSibling().text():"");
            Element retireClothEle = textEle.select(".column:containsOwn(退役球衣:)").first();
            teamEntity.setRetireCloth(retireClothEle != null ? retireClothEle.nextElementSibling().text() : "");
            callableResultBack.isSuccess = true;
            callableResultBack.errorUrl = "";
            callableResultBack.entity = teamEntity;
        } catch (Exception e) {
            e.printStackTrace();
            callableResultBack.isSuccess = false;
            callableResultBack.errorUrl = url;
            callableResultBack.entity = null;
        }
        return callableResultBack;
    }


    @Override
    protected List<String> getAllUrl() {
        List<String> results = new ArrayList<String>();
        try {
            Document teamListDoc = Jsoup.connect("http://www.stat-nba.com/teamList.php").userAgent(Constants.USERAGENT).get();
            Element tableEle = teamListDoc.select("table.stat_box").first();
            Element trEle = tableEle.select("tbody tr").last();
            Elements teamEle = trEle.select("div.team");
            String urlTemp = "";
            for(Element e : teamEle){
                urlTemp = "http://www.stat-nba.com/"+e.select("a").first().attr("href").substring(2);
                results.add(urlTemp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;

    }

    @Override
    protected <T extends AbstractEntity> T saveEntity(CallableResultBack callableResultBack) {
        TeamEntity t = (TeamEntity) callableResultBack.entity;
        downloadImage(t.getLogoStore(),t.getLogo(),Constants.TEAM_TYPE);
        return (T) (teamService.addTeam(t));
    }
}
