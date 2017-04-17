package com.statestr.crawl;


import com.alibaba.fastjson.JSON;
import com.statestr.entity.AbstractEntity;
import com.statestr.util.Constants;
import com.statestr.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ruantianbo on 2017/3/28.
 */
public abstract class AbstractCrawl {

    protected Boolean downloadImage(String imageUrl,String fileName,String type){
        if(StringUtils.notNullOrEmpty(imageUrl,fileName,type)){
            try{
                URL url = new URL(imageUrl);
                URLConnection urlConnection = url.openConnection();
                InputStream is = urlConnection.getInputStream();
                File storeFile = null;
                if(type.equals(Constants.PLAYER_TYPE)){
                    storeFile = new File(Constants.STORE_DESTINATION.get(Constants.PLAYER_TYPE)+fileName);
                }else if (type.equals(Constants.TEAM_TYPE)){
                    storeFile = new File(Constants.STORE_DESTINATION.get(Constants.TEAM_TYPE)+fileName);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(storeFile);

                byte[] bytes = new byte[1024];
                int byteCount = 0;
                int byteWrite = 0;
                while((byteCount=is.read())!=-1){
                    fileOutputStream.write(byteCount);
                }
                is.close();
                fileOutputStream.close();
                return true;
            }catch (IOException e) {
                System.out.println("failure to get imageUrl:"+imageUrl);
                return false;
            }
        }
        return false;
    }

    class CallableResultBack<T extends AbstractEntity>{
        public Boolean isSuccess = false;
        public String errorUrl;
        public T entity;

    }

    class FetchRunnable implements Callable<CallableResultBack> {
        private String url;

        public FetchRunnable(String url) {
            this.url = url;
        }

        public CallableResultBack call() {
            //每个crawl子类继承，实现具体都爬去方法
            System.out.println("Thread "+Thread.currentThread().getName()+"start to fetch url:"+url);
            return fetchDetail(url);
        }
    }

    //通过一个url获取 数据信息,不保存信息
    protected abstract CallableResultBack fetchDetail(String url);
    //获取所有要爬去都url
    protected abstract List<String> getAllUrl();
    //保存数据
    protected abstract <T extends AbstractEntity> T saveEntity(CallableResultBack callableResultBack);

    public void start(){
        Date nowBefore = new Date();
        System.out.println("Start Time:" + nowBefore);
        List<String> allUrl = getAllUrl();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<String> errorList = new ArrayList<>();
        int successCount = 0;
        List<Future<CallableResultBack>> callbackResults = new ArrayList<Future<CallableResultBack>>();
        for(int i=0;i<allUrl.size();i++){
            Future<CallableResultBack> future = executorService.submit(new FetchRunnable(allUrl.get(i)));
            callbackResults.add(future);
        }
        for(Future<CallableResultBack> callbackResultItem : callbackResults){
            while (!callbackResultItem.isDone()); //没有完成一直等待
            try {
                if(callbackResultItem.get().isSuccess){
                    successCount ++;
                    System.out.println((Object) this.saveEntity(callbackResultItem.get()));
                } else{
                    errorList.add(callbackResultItem.get().errorUrl);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
        System.out.println("Total Success:"+successCount+",Failure:"+errorList.size());
        System.out.println("Total Failure:"+errorList.toString());

        System.out.println("Try to fix errorList");
        System.out.println("=====================================================");

        fixData(errorList);

        Date nowAfter = new Date();
        long difftime = nowAfter.getTime() - nowBefore.getTime();
        int min = ((int) (difftime / 1000)) / 60;
        int sec = ((int) (difftime / 1000)) % 60;
        System.out.println("End Time:" + nowAfter + " ,Spend:" + min + " mins," + sec + " secs");
    }

    protected void fixData(List<String> errorList){
        for(String fixUrl : errorList){
            if(!fetchDetail(fixUrl).isSuccess){
                System.out.println("success fetch:"+fixUrl);
            }else{
                System.out.println("Failure fetch:"+fixUrl);
            }
        }
    }

}
