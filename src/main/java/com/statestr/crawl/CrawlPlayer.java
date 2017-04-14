//package com.statestr.crawl;
//
//
//
//import com.statestr.entity.PlayerEntity;
//import com.statestr.service.PlayerService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.*;
//
///**
// * Created by ruantianbo on 2017/3/27.
// */
//public class CrawlPlayer extends AbstractCrawl{
//    private static final Boolean ISDEV = Boolean.FALSE;
//    private static final Boolean ISFIX = Boolean.FALSE;
//    @Autowired
//    private PlayerService playerService;
//
//    static class SaleryDetail{
//        public String season;
//        public String team;
//        public String doller;
//
//        public SaleryDetail(){}
//        public SaleryDetail(String season, String team, String doller) {
//            this.season = season;
//            this.team = team;
//            this.doller = doller;
//        }
//    }
//    public static void fixData(){
//        //[http://www.stat-nba.com/player/255.html, http://www.stat-nba.com/player/2672.html, http://www.stat-nba.com/player/1971.html,
//        // http://www.stat-nba.com/player/3079.html, http://www.stat-nba.com/player/973.html, http://www.stat-nba.com/player/3849.html,
//        // http://www.stat-nba.com/player/2328.html]
//        String[] fixList = "http://www.stat-nba.com/player/255.html,http://www.stat-nba.com/player/2672.html,http://www.stat-nba.com/player/1971.html,http://www.stat-nba.com/player/3079.html,http://www.stat-nba.com/player/973.html,http://www.stat-nba.com/player/3849.html,http://www.stat-nba.com/player/2328.html".split(",");
//        for(String fixItem : fixList){
//            PlayerEntity p =fetchPlayerDetail(fixItem);
//            if(p != null && playerService.addPlayer(p)!=null){
//                syso("success fetch:"+fixItem);
//            }else{
//                syso("falure fetch:"+fixItem);
//            }
//        }
//    }
//    public static void fixData(List<String> fixList){
//        //[http://www.stat-nba.com/player/255.html, http://www.stat-nba.com/player/2672.html, http://www.stat-nba.com/player/1971.html,
//        // http://www.stat-nba.com/player/3079.html, http://www.stat-nba.com/player/973.html, http://www.stat-nba.com/player/3849.html,
//        // http://www.stat-nba.com/player/2328.html]
//        UserDaoImpl userDaoImpl = new UserDaoImpl();
//        for(String fixItem : fixList){
//            PlayerEntity p =fetchPlayerDetail(fixItem);
//            if(p != null && userDaoImpl.insertPlayer(p)!=null){
//                syso("success fetch:"+fixItem);
//            }else{
//                syso("falure fetch:"+fixItem);
//            }
//        }
//    }
//    public static void main(String[] args){
//        if(ISFIX){
//            fixData();
//            return;
//        }
//        Date nowBefore = new Date();
//        System.out.println("Start Time:" + nowBefore);
//        int count = 0;
//        List<String> allFetchPlayerList = getAllFetchPlayerList();
//        List<String> errorPlayer = new ArrayList<String>();
//        List<Future<CallableResultBack>> callbackResults = new ArrayList<Future<CallableResultBack>>();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for(String fetchItem : allFetchPlayerList) {
//            Future<CallableResultBack> future = executorService.submit(new FetchRunnable(fetchItem));
//            callbackResults.add(future);
//        }
//
//        for(Future<CallableResultBack> callbackResultItem : callbackResults){
//            while (!callbackResultItem.isDone()); //没有完成一直等待
//            try {
//                errorPlayer.addAll(callbackResultItem.get().errorList);
//                count += callbackResultItem.get().successCount;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }finally {
//                executorService.shutdown();
//            }
//        }
//        syso("Total Success:"+count+",Failure:"+errorPlayer.size());
//        syso("Total Failure:"+errorPlayer.toString());
//
//        syso("Try to fix errorList");
//        syso("=====================================================");
//        fixData(errorPlayer);
//        Date nowAfter = new Date();
//        long difftime = nowAfter.getTime() - nowBefore.getTime();
//        int min = ((int) (difftime / 1000)) / 60;
//        int sec = ((int) (difftime / 1000)) % 60;
//        System.out.println("End Time:" + nowAfter + " ,Spend:" + min + " mins," + sec + " secs");
//    }
//    static class CallableResultBack{
//        public int successCount = 0;
//        public List<String> errorList = new ArrayList<String>();
//
//    }
//    static class FetchRunnable implements Callable<CallableResultBack> {
//        private String url;
//
//        public FetchRunnable(String url) {
//            this.url = url; // A - Z
//        }
//
//        public CallableResultBack call() {
//            syso("线程" + Thread.currentThread().getName() + "启动，将会爬去" + url);
//            CallableResultBack resultBack = new CallableResultBack();
//            try {
//                UserDaoImpl userDaoImpl = new UserDaoImpl();
//                Document playPageDoc = Jsoup.connect(url).userAgent(Constants.USERAGENT).get();
//                Element realPlayerWrapperDiv = playPageDoc.select("#background div:containsOwn(球员搜索结果)").get(0).nextElementSibling();
//                Elements realPlayer = realPlayerWrapperDiv.select("a[href^=./player]");
//                for (Element realPlayerItem : realPlayer) {
//                    String hrefTemp = realPlayerItem.attr("href");
//                    String playerDetailUrl = hrefTemp.substring(hrefTemp.indexOf("/") + 1);
//                    PlayerEntity playerEntity = fetchPlayerDetail(Constants.BASEURL + playerDetailUrl); // /player/812.html
//                    if (playerEntity != null && userDaoImpl.insertPlayer(playerEntity) != null) {
//                        syso("success while fetch :" + playerEntity.getDetailUrl());
//                        resultBack.successCount++;
//                        //count ++;
//                    } else {
//                        resultBack.errorList.add(Constants.BASEURL + playerDetailUrl);
//                        //errorPlayer.add(playerEntity.getDetailUrl());
//                    }
//                }
//                syso("Done Fetch http://www.stat-nba.com/playerList.php?il=" + url.charAt(url.length() - 1));
//                syso("Success Count:" + resultBack.successCount + ",failure Count:" + resultBack.errorList.size());
//
//            } catch (Exception e) {
//                System.out.println("Failure while fetch url :" + url);
//                System.out.println(e.getCause());
//            }
//            return resultBack;
//        }
//    }
//
//    /**通过url 获取 player*/
//    private static PlayerEntity fetchPlayerDetail(String playerUrl){
//
//        PlayerEntity p = new PlayerEntity();
//        try {
//            Document playerDetailDoc = Jsoup.connect(playerUrl).userAgent(Constants.USERAGENT).get();
//            p.setDetailUrl(playerUrl);
//            String[] nameTemp = playerDetailDoc.select("div.name").first().ownText().trim().split("/");
//            if(nameTemp.length == 2){
//                p.setNameCh(nameTemp[0]);
//                p.setNameEn(nameTemp[1]);
//            }else{
//                p.setNameCh(nameTemp[0]);
//                p.setNameEn(nameTemp[0]);
//            }
//            p.setCode(String.valueOf(p.getNameEn().charAt(0)).toUpperCase());
//            Element playerInfoPanel =playerDetailDoc.select("div.playerinfo").first();
//            Element imageElement = playerInfoPanel.select("div.image img[src^=/image/playerImage]").first();
//            String pieceImageUrl = imageElement.attr("src");
//            String fullImageUrl = Constants.BASEURL+pieceImageUrl.substring(1);
//            String fileName = pieceImageUrl.substring(pieceImageUrl.lastIndexOf("/")+1);
//            p.setAvator(fileName);
//            //下载文件，等所有数据都ok 都时候再打开
//            if(!ISDEV && !("img_middle_common.jpg".equalsIgnoreCase(fileName))){
//                if(downloadImage(fullImageUrl,fileName,Constants.PLAYER_TYPE)){
//                    p.setAvator("img_middle_common.jpg");
//                }
//            }
//            Element playerDetailElement = playerInfoPanel.select("div.detail").first();
//            p.setFullName(getPlayerDetail("全　　名:",playerDetailElement));
//            p.setPosition(getPlayerDetail("位　　置:",playerDetailElement));
//            p.setHeight(getPlayerDetail("身　　高:",playerDetailElement));
//            p.setWeight(getPlayerDetail("体　　重:",playerDetailElement));
//            p.setBirthday(getPlayerDetail("出生日期:",playerDetailElement));
//            p.setBirthcity(getPlayerDetail("出生城市:",playerDetailElement));
//            p.setHighSchool(getPlayerDetail("高　　中:",playerDetailElement));
//            p.setCollege(getPlayerDetail("大　　学:",playerDetailElement));
//            p.setCloth(getPlayerDetail("球衣号码:",playerDetailElement));
//            p.setTalentShow(getPlayerDetail("选秀情况:",playerDetailElement));
//            Elements salaryElements = playerDetailDoc.select("div#player_salary table.stat_box tbody tr");
//            List<SaleryDetail> saleryDetailList = new ArrayList<SaleryDetail>();
//            for(Element salaryItem : salaryElements){
//                Elements tds = salaryItem.select("td");
//                if(tds!=null && tds.size()==3 && "current".equalsIgnoreCase(tds.first().attr("class"))){
//                    SaleryDetail saleryDetail = new SaleryDetail();
//                    saleryDetail.season = tds.get(0).ownText();
//                    saleryDetail.team = tds.get(1).child(0).ownText();
//                    saleryDetail.doller = tds.get(2).ownText();
//                    saleryDetailList.add(saleryDetail);
//                }
//            }
//            p.setSalary(JSON.toJSONString(saleryDetailList));
//            return p;
//        } catch (IOException e) {
//            syso("Failure to fetch url:"+playerUrl);
//            return null;
//        }
//    }
//    public static String getPlayerDetail(String flagStr, Element playerDetailElement){
//        try {
//            String resultStr = playerDetailElement.select("div:containsOwn(" + flagStr + ")").first().parent().ownText();
//            return resultStr.trim();
//        }catch (Exception e){
//            //syso("failure while fetch "+flagStr);
//            return "";
//        }
//
//    }
//
//
//    private static List<String> getAllFetchPlayerList(){
//        List<String> resultAllFetchPlayerList = new ArrayList<String>();
//        for(int i = 'Q';i<='Z';i++){
//            StringBuffer sb = new StringBuffer();
//            sb.append(Constants.BASEURL).append("playerList.php?il=").append((char)i);
//            resultAllFetchPlayerList.add(sb.toString());
//        }
//        return resultAllFetchPlayerList;
//    }
//
//    private static void syso(String str){
//        System.out.println(str);
//    }
//
//}
