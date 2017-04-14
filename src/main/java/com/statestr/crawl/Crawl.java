//package com.statestr.crawl;
//
//import com.jugg.util.Constants;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by ruantianbo on 2017/3/27.
// */
//public class Crawl {
//    private static final String[] years = {"2014", "2015", "2016"};
//    public static void main(String[] args) throws InterruptedException {
//        Date nowBefore = new Date();
//        System.out.println("Start Time:" + nowBefore);
//        List<String> allFetchUrl = getAllFetchUrl();
//
//        for(String fetchURLItem : allFetchUrl) {
//            try {
//                Document document = Jsoup.connect(fetchURLItem).timeout(3000).url(Constants.USERAGENT).get();
//
//            }catch (IOException ex){
//                ex.printStackTrace();
//                System.out.println("Error while fetch url:"+fetchURLItem);
//            }
//        }
//
//        Date nowAfter = new Date();
//        long difftime = nowAfter.getTime() - nowBefore.getTime();
//        int min = ((int) (difftime / 1000)) / 60;
//        int sec = ((int) (difftime / 1000)) % 60;
//        System.out.println("End Time:" + nowAfter + " ,Spend:" + min + " mins," + sec + " secs");
//    }
//
//
//
//
//    private static List<String> getAllFetchUrl() {
//        List<String> resultAllFetchUrl = new ArrayList<String>();
//        for (String year : years) {
//            for (int i = 1; i <= 12; i++) {
//                StringBuffer sb = new StringBuffer();
//                sb.append(Constants.BASEURL).append("gameList_simple-").append(year).append("-");
//                if (i < 10) {
//                    sb.append("0");
//                }
//                sb.append(i).append(".html");
//                resultAllFetchUrl.add(sb.toString());
//            }
//
//        }
//        return resultAllFetchUrl;
//    }
//}
