package com.statestr.crawl;

import com.alibaba.fastjson.JSON;
import com.statestr.dto.ScoreDetailDto;
import com.statestr.entity.*;
import com.statestr.service.MatchService;
import com.statestr.service.PlayerService;
import com.statestr.service.TeamService;
import com.statestr.util.Constants;
import com.statestr.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ruantianbo on 2017/4/10.
 */
public class CrawlMatch extends AbstractCrawl{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private TeamService teamService;
    private PlayerService playerService;
    private MatchService matchService;
    private final String[] FETCH_YEAR = {"2017"};
    private final String[] VIDEO_FLAG = {"腾讯第一节","腾讯第二节","腾讯第三节","腾讯第四节","腾讯加时一","腾讯加时二","腾讯加时三","腾讯加时四","腾讯加时五","腾讯加时六"};
    public CrawlMatch(TeamService teamService, PlayerService playerService, MatchService matchService){
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
    }
    @Override
    protected CallableResultBack fetchDetail(String url) {
        CallableResultBack callableResultBack = new CallableResultBack();
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setDetailUrl(url);
        try {
            Document document = Jsoup.connect(url).userAgent(Constants.USERAGENT).get();
            matchEntity.setHappendTime(dateFormat.parse((document.select("font:containsOwn(美国当地时间)").first().parent().ownText())));
            Element basicEle = document.select("div.basic").first();
            Elements teamEles = basicEle.select("div.team");
            Element awayTeamEle = teamEles.get(0);
            Element homeTeamEle = teamEles.get(1);
            matchEntity.setAwayTeam(teamService.findByDetailUrlLike(awayTeamEle.select("a[style=font-size:14px;]").attr("href")));
            matchEntity.setHomeTeam(teamService.findByDetailUrlLike(homeTeamEle.select("a[style=font-size:14px;]").attr("href")));
            Element scoreBoxEles = basicEle.select(".scorebox").first();
            Elements scoreEles = scoreBoxEles.select(".score");
            matchEntity.setAwayScore(StringUtils.intStr2int(scoreEles.get(0).ownText()));
            matchEntity.setHomeScore(StringUtils.intStr2int(scoreEles.get(1).ownText()));
            Elements scoreTable = scoreBoxEles.select("table");
            Elements awayTrs = scoreTable.get(0).select("tr");
            Elements homeTrs = scoreTable.get(1).select("tr");
            List<ScoreDetailDto> scoreDetailDtos = new ArrayList<ScoreDetailDto>();
            for(int i=0;i< awayTrs.size();i++){
                ScoreDetailDto scoreDetailDto = new ScoreDetailDto();
                Elements awayTds = awayTrs.get(i).select("td");
                Elements homeTds = homeTrs.get(i).select("td");
                scoreDetailDto.setLabel(awayTds.get(0).ownText());
                scoreDetailDto.setAwayScore(Integer.parseInt(awayTds.get(1).ownText()));
                scoreDetailDto.setHomeScore(Integer.parseInt(homeTds.get(0).ownText()));
                scoreDetailDtos.add(scoreDetailDto);
            }
            //还差一个 video  http://www.stat-nba.com/game/38061.html
            Element detailEle = document.select(".detail").first();
            for(int i=0;i<scoreDetailDtos.size();i++){
                Elements videoTempEles = detailEle.select("a:containsOwn("+VIDEO_FLAG[i]+")");
                if(videoTempEles.size() >0){
                    Element videoEle = videoTempEles.first();
                    scoreDetailDtos.get(i).setVideo(videoEle.attr("href"));
                }
            }
            matchEntity.setScoreDetail(JSON.toJSONString(scoreDetailDtos));

            Element awayDetailPanelEle = document.select("div#stat_box"+matchEntity.getAwayTeam().getShortName()).first();
            Element homeDetailPanelEle = document.select("div#stat_box"+matchEntity.getHomeTeam().getShortName()).first();
            Elements awayPlayerTrEles = awayDetailPanelEle.select("tbody tr.sort");
            Elements homePlayerTrEles = homeDetailPanelEle.select("tbody tr.sort");

            Element awayTeamTrEles = awayDetailPanelEle.select("tbody tr.team_all_content").first();
            Element homeTeamTrEles = homeDetailPanelEle.select("tbody tr.team_all_content").first();

            Set<PlayerInMatchEntity> awayPlayerSet = playerDetailFetch(awayPlayerTrEles,matchEntity,false);
            Set<PlayerInMatchEntity> homePlayerSet = playerDetailFetch(homePlayerTrEles,matchEntity,true);

            TeamInMatchEntity awayTeamInMatch = teamDetailFetch(awayTeamTrEles);
            TeamInMatchEntity homeTeamInMatch = teamDetailFetch(homeTeamTrEles);
            awayTeamInMatch.setTeamEntity(matchEntity.getAwayTeam());
            homeTeamInMatch.setTeamEntity(matchEntity.getHomeTeam());

            awayTeamInMatch.setMatch(matchEntity);
            homeTeamInMatch.setMatch(matchEntity);

            awayTeamInMatch.setHome(false);
            homeTeamInMatch.setHome(true);

            //添加PlayerSet
            matchEntity.getPlayerInMatchSet().addAll(homePlayerSet);
            matchEntity.getPlayerInMatchSet().addAll(awayPlayerSet);

            //添加TeamSet
            matchEntity.getTeamInMatchSet().add(homeTeamInMatch);
            matchEntity.getTeamInMatchSet().add(awayTeamInMatch);


            callableResultBack.isSuccess = true;
            callableResultBack.errorUrl = "";
            callableResultBack.entity = matchEntity;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            callableResultBack.isSuccess = false;
            callableResultBack.errorUrl = url;
            callableResultBack.entity = null;
        }
        return callableResultBack;
    }

    private TeamInMatchEntity teamDetailFetch(Element trEle){
        TeamInMatchEntity t = new TeamInMatchEntity();
        String totalPlayerCount = trEle.select("td.player_id").first().ownText();

        t.setPlayerOnCourt(StringUtils.intStr2int(totalPlayerCount.substring(0,totalPlayerCount.length()-1)));
        getDetail(trEle,t);

        return t;
    }
    private Set<PlayerInMatchEntity> playerDetailFetch(Elements trsEle,MatchEntity matchEntity,boolean ishome){
        Set<PlayerInMatchEntity> playerInMatchEntities = new HashSet<PlayerInMatchEntity>();
        for(Element awayTr : trsEle){
            PlayerInMatchEntity playerInMatchEntity = new PlayerInMatchEntity();
            PlayerEntity p = playerService.findByDetailUrlLike(awayTr.select(".player_name_out a[href^=/player]").first().attr("href"));
            if(p!=null){
                playerInMatchEntity.setPlayer(p);
            }else{
                // 这里可以fetch player
                continue;
            }
            if(detailUtil(awayTr,"gs")=="1"){
                playerInMatchEntity.setFirst(Boolean.TRUE);
            }else{
                playerInMatchEntity.setFirst(Boolean.FALSE);
            }
            playerInMatchEntity.setTimeOnCourt(StringUtils.intStr2int(detailUtil(awayTr,"mp")));
            playerInMatchEntity.setHome(ishome);
            getDetail(awayTr,playerInMatchEntity);
            playerInMatchEntity.setMatch(matchEntity);
            playerInMatchEntities.add(playerInMatchEntity);
        }
        return playerInMatchEntities;
    }

    private void getDetail(Element detailItem,AbstractInMatchEntity inMatchEntity){

        inMatchEntity.setShootInPercent(StringUtils.percentStr2double(detailUtil(detailItem,"fgper")));
        inMatchEntity.setHit(StringUtils.intStr2int(detailUtil(detailItem,"fg")));
        inMatchEntity.setShot(StringUtils.intStr2int(detailUtil(detailItem,"fga")));

        inMatchEntity.setThreePointShootInPercent(StringUtils.percentStr2double(detailUtil(detailItem,"threepper")));
        inMatchEntity.setThreePointHit(StringUtils.intStr2int(detailUtil(detailItem,"threep")));
        inMatchEntity.setThreePointShot(StringUtils.intStr2int(detailUtil(detailItem,"threepa")));

        inMatchEntity.setPenaltyShootInPercent(StringUtils.percentStr2double(detailUtil(detailItem,"ftper")));
        inMatchEntity.setPenaltyHit(StringUtils.intStr2int(detailUtil(detailItem,"ft")));
        inMatchEntity.setPenaltyShot(StringUtils.intStr2int(detailUtil(detailItem,"fta")));

        inMatchEntity.setRealShotPercent(StringUtils.percentStr2double(detailUtil(detailItem,"ts")));

        inMatchEntity.setBackboard(StringUtils.intStr2int(detailUtil(detailItem,"trb")));
        inMatchEntity.setFrontCourt(StringUtils.intStr2int(detailUtil(detailItem,"orb")));
        inMatchEntity.setBackCourt(StringUtils.intStr2int(detailUtil(detailItem,"drb")));
        inMatchEntity.setAssist(StringUtils.intStr2int(detailUtil(detailItem,"ast")));
        inMatchEntity.setGrab(StringUtils.intStr2int(detailUtil(detailItem,"stl")));
        inMatchEntity.setBlock(StringUtils.intStr2int(detailUtil(detailItem,"blk")));
        inMatchEntity.setFault(StringUtils.intStr2int(detailUtil(detailItem,"tov")));
        inMatchEntity.setFoul(StringUtils.intStr2int(detailUtil(detailItem,"pf")));
        inMatchEntity.setScore(StringUtils.intStr2int(detailUtil(detailItem,"pts")));

    }

    //detailItem is tr
    private String detailUtil(Element detailItem,String selectorClass){
        Assert.notNull(detailItem,"detailItem should not null");
        Elements detailTdEles = detailItem.select("td."+selectorClass);
        if(detailTdEles.size()>0){
            Element detailTdEle = detailTdEles.first();
            return detailTdEle.ownText().trim();
        }
        return "";
    }
    public static void main(String[] args){
        CrawlMatch c = new CrawlMatch(null,null,null);
        System.out.println(JSON.toJSONString(c.getAllUrl()));
    }
    @Override
    protected List<String> getAllUrl() {
        List<String> allUrl = new ArrayList<String>();
        List<String> allMouthUrl = new ArrayList<String> ();
        String mouth = "";
        String urlTemp = "";
        for(String yearStr : FETCH_YEAR){
            for(int i=1;i<=12;i++){
                mouth = "";
                urlTemp = "";
                if(i<10){
                    mouth += "0";
                }
                mouth += i;
                urlTemp = "http://www.stat-nba.com/gameList_simple-"+yearStr+"-"+mouth+".html";
                allMouthUrl.add(urlTemp);
            }
        }

        for(String mouthUrlItem : allMouthUrl){
            try {
                Document document = Jsoup.connect(mouthUrlItem).userAgent(Constants.USERAGENT).get();
                Elements linkEles = document.select("a[href^=game]");
                for (Element linkItem : linkEles){
                    allUrl.add("http://www.stat-nba.com/"+linkItem.attr("href"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return allUrl;
//        return new ArrayList<String>(){{
//            add("http://www.stat-nba.com/game/40247.html");
//        }};
    }

    @Override
    protected <T extends AbstractEntity> T saveEntity(CallableResultBack callableResultBack) {
        //System.out.println(((MatchEntity)callableResultBack.entity));
        //return null;
//        MatchEntity m = (MatchEntity)callableResultBack.entity;
//        for(PlayerInMatchEntity pi : m.getAwayPlayerInMatchSet()){
//            System.out.println(pi.getMatch().getId());
//        }
        return (T)matchService.addMatch((MatchEntity)callableResultBack.entity);
    }
}
