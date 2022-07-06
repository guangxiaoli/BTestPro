/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * @author : huarong
 * @version 1: HttpClientUtils.java, v 0.1 2022-04-15 15:44 lixiaoguang
 */
public class HttpClientUtils {

    /**
     * 默认超时时间
     */
    private static final int TIMEEOUT = 5000;


    public  static  String doGet(String url, Map<String, String> headersMap){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseStr = null;

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(TIMEEOUT)
                .setConnectionRequestTimeout(TIMEEOUT)
                .setSocketTimeout(TIMEEOUT)
                .setRedirectsEnabled(true).build();


        try {
            HttpGet httpGet = new HttpGet(url);
            if(!CollectionUtils.isEmpty(headersMap)){
                httpGet=addHeaders(httpGet, headersMap);
            }
            httpGet.setConfig(requestConfig);
            httpResponse = httpclient.execute(httpGet);
            HttpEntity resEntity = httpResponse.getEntity();
            if (resEntity != null) {
                responseStr = EntityUtils.toString(resEntity, "utf-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }



    public  static  String doPost(String url, String jsonStr, Map<String, String> headersMap){
        // 对象和JSON等均转为json格式字符串再转StringEntity
        StringEntity entity=new StringEntity(jsonStr, "UTF-8");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = null;


        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(TIMEEOUT)
                .setConnectionRequestTimeout(TIMEEOUT)
                .setSocketTimeout(TIMEEOUT)
                .setRedirectsEnabled(true).build();

        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            //TODO: 最好放在headersMap
            if(!CollectionUtils.isEmpty(headersMap)){
                httpPost=addHeaders(httpPost, headersMap);
            }
           // httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
            httpPost.setEntity(entity);
            response = httpclient.execute(httpPost);

            HttpEntity resEntity = response.getEntity();
            System.out.println(JSON.toJSONString(resEntity));

            if (resEntity != null) {
                resultStr = EntityUtils.toString(resEntity, "UTF-8");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }



    public  static  String doDelete(String url, Map<String, String> headersMap){
        //同get
        // HttpDelete delete = new HttpDelete(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseStr = null;

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(TIMEEOUT)
                .setConnectionRequestTimeout(TIMEEOUT)
                .setSocketTimeout(TIMEEOUT)
                .setRedirectsEnabled(true).build();


        try {
           // HttpGet httpGet = new HttpGet(url);
            HttpDelete httpGet = new HttpDelete(url);
            if(!CollectionUtils.isEmpty(headersMap)){
                httpGet=addHeaders(httpGet, headersMap);
            }
            httpGet.setConfig(requestConfig);
            httpResponse = httpclient.execute(httpGet);
            HttpEntity resEntity = httpResponse.getEntity();
            if (resEntity != null) {
                responseStr = EntityUtils.toString(resEntity, "utf-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }

    public  static  String doPut(){
        return  null;
    }



    private  static HttpGet  addHeaders(HttpGet httpGet, Map<String, String> HeadersMap){
        if (CollectionUtils.isEmpty(HeadersMap)) {
            return httpGet;
        }
        for (String key : HeadersMap.keySet()) {
            httpGet.addHeader(key, HeadersMap.get(key));
        }
        return httpGet;
    }


    private  static HttpPost  addHeaders(HttpPost httpPost, Map<String, String> HeadersMap){
        if (CollectionUtils.isEmpty(HeadersMap)) {
            return httpPost;
        }
        for (String key : HeadersMap.keySet()) {
            httpPost.addHeader(key, HeadersMap.get(key));
        }
        return httpPost;
    }

    private  static HttpDelete  addHeaders(HttpDelete httpdelete, Map<String, String> HeadersMap){
        if (CollectionUtils.isEmpty(HeadersMap)) {
            return httpdelete;
        }
        for (String key : HeadersMap.keySet()) {
            httpdelete.addHeader(key, HeadersMap.get(key));
        }
        return httpdelete;
    }


}