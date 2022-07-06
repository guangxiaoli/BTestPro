/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.entity.StringEntity;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : huarong
 * @version 1: DingDingMessageUtils.java, v 0.1 2022-04-21 11:04 lixiaoguang
 */
public class DingDingMessageUtils {
    private  static  final   String secret = "SEC6IUENRFFFFFFf58d";
    public static void main(String[] args) {
       String res= sendDingMes();
        System.out.println(res);
    }



    public static   String sendDingMes() {

        Long timestamp=System.currentTimeMillis();
        String sign=dingSign(timestamp);
        String url="https://oapi.dingtalk.com/robot/send?access_token=e0bFSFSFFFSFb0"+"&timestamp="+timestamp+"&sign="+sign;
        JSONObject obj=fullMsgText();
        StringEntity se = new StringEntity(obj.toJSONString(),"utf-8");

        Map<String,String> headerMap=new HashMap<>();
        headerMap.put("Content-Type","application/json; charset=utf-8");


        return HttpClientUtils.doPost(url, JSON.toJSONString(obj),headerMap);

    }



    private static JSONObject fullMsgText(){
        JSONObject  atObj=new JSONObject();
        atObj.put("isAtAll",false);
        String[]  mobl=new String[]{"18980561412"};
        atObj.put("atMobiles",mobl);

        JSONObject  textObj=new JSONObject();
        textObj.put("content","你好我好大家好！");


        JSONObject  obj=new JSONObject();
        obj.put("at",atObj);
        obj.put("text",textObj);
        obj.put("msgtype","text");
        return  obj;

    }


    private static String dingSign(Long timestamp){
        String sign="";
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
           sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            System.out.println(sign);
        }catch (Exception e){
                e.printStackTrace();
        }finally {

        }
        return  sign;
    }
}