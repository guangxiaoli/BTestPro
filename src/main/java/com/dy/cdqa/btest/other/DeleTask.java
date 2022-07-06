/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.other;

import com.dy.cdqa.btest.utils.HttpClientUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : huarong
 * @version 1: DeleTask.java, v 0.1 2022-04-11 11:42 lixiaoguang
 */
public class DeleTask {

    private static String jsonpath= "C:\\Users\\lixiaoguang\\Desktop\\test\\line.txt";
    static String url="https://devops.aliyun.com/projex/api/workitem/workitem/";

    public static void main(String[] args) {
        //DeleTask.readFile(jsonpath);
        Map<String,String> map=new HashMap<String,String>();
        map.put("x-csrf-token","kNKO2UVZ-daFF8FvOvpA0oWQKG6quCcstWN8");
        map.put("cookie","cna=923gGoUlqUECAWcbGHfmq7zk; t=5e3fa73ed36afd2b8484ab9232e1c49e; login_aliyunid_pk=1693645411130876; aliyun_lang=zh; aliyun_choice=CN; currentRegionId=cn-hangzhou; AONE_SESSION=232495fb-ebe0-4317-badb-fc62e776d6e8; _samesite_flag_=true; cookie2=1202c294e916312a89693bb1999d7c12; _tb_token_=3b790ee310e51; _hvn_login=6; csg=2b24719a; login_aliyunid=\"dingtalk_jy****\"; login_aliyunid_ticket=t5KHKoLgS2MXwQfNX5tc2sb6MZ3L1QA0_of_BNpwU_TOTNChZBoeM1KJexdfb9zhYnsN5Zos6qISCrRt7mGxbigG2Cd4fWaCmBZHIzsgdZq64XXWQgyKFeuf0vpmV*s*CT58JlM_1t$w3bK$1PlmXnIx0; login_aliyunid_csrf=_csrf_tk_1241550551740656; hssid=1gQuWevjiFEOcSZ0KunhI-Q1; hsite=6; aliyun_country=CN; aliyun_site=CN; LOGIN_ALIYUN_PK_FOR_TB=1693645411130876; TEAMBITION_SESSIONID=eyJ1aWQiOiI2MjE1OTIxODhhZjVkMzllYjMzMDY4YzUiLCJhdXRoVXBkYXRlZCI6MTY0OTkwODQxNDM0MywidXNlciI6eyJfaWQiOiI2MjE1OTIxODhhZjVkMzllYjMzMDY4YzUiLCJuYW1lIjoi6Iqx6I2jIiwiZW1haWwiOiJhY2NvdW50c182MjE1OTIxODk3M2ZjNzAwMmQyOGQ4ZDBAbWFpbC50ZWFtYml0aW9uLmNvbSIsImF2YXRhclVybCI6Imh0dHBzOi8vdGNzLWRldm9wcy5hbGl5dW5jcy5jb20vdGh1bWJuYWlsLzExMmYyMmE3MDQ4MWYyMDU5YTEyOGM3Y2JiNDAzMmJmNjQwZS93LzEwMC9oLzEwMCIsInJlZ2lvbiI6InVzIiwibGFuZyI6IiIsImlzUm9ib3QiOmZhbHNlLCJvcGVuSWQiOiIiLCJwaG9uZUZvckxvZ2luIjoiIiwiY3JlYXRlZCI6IjIwMjItMDItMjNUMDE6NDc6MDQuMjA3WiJ9LCJsb2dpbkZyb20iOiIifQ==; TEAMBITION_SESSIONID.sig=7ppvzRnUX6LIIJnRSp31X4_oCuA; ak_user_locale=zh_CN; isg=BI6OVQo-YJj__dSEc7amazaJ32RQD1IJH9hZlLjXvBFMGy51IJ2hGcDaVkd3OEoh; l=eBjlxDDnLhYiJ3zYBOfZourza77ttIRYjuPzaNbMiOCP9Q1B5KZhW6qj_qY6CnhVh6P2R3lrOrBwBeYBqQd-nxv96aiE7yDmn; tfstk=cNGPB36GNQdztPNBB7NeAWnNHhuRZun-vGagEh4MY88B4ywlidjLoh-QC8QVx8f..; TB_ACCESS_TOKEN=eyJhbGciOiJFZERTQSIsInR5cCI6IkpXVCJ9.eyJhcHAiOiI1ZTczNzAyMjNiYjY5MzczNjYwNjAxYTQiLCJhdWQiOiIiLCJleHAiOjE2NTA4MTA5NTAsImlhdCI6MTY1MDU1MTc1MCwiaXNzIjoidHdzIiwianRpIjoiMlRxVDg4YXhWX3RZN2F0YS1va2RYbE14eEtWLUVLbjdKSmkwN09tMVdvZz0iLCJyZW5ld2VkIjoxNjQ5OTA4NDE0MzQzLCJzY3AiOlsiYXBwX3VzZXIiXSwic3ViIjoiNjIxNTkyMTg4YWY1ZDM5ZWIzMzA2OGM1IiwidHlwIjoiYWNjZXNzX3Rva2VuIn0.e0YgmQW8oHETovmSvMSF8JZ76UVaBHeMRBB9_4aT_DyN5c6vjnXm97uvlY9r4YCSM-7PXopoxMery1ZcIBliDg");
       String s="621592188af5d39eb33068c5";
        String temo=url+s;
        HttpClientUtils.doDelete(temo,map);



    }
    // 读取文件内容
    public static String readFile(String jsonpath) {//路径
        File file = new File(jsonpath);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String s = null;
            Map<String,String> map=new HashMap<String,String>();
            map.put("x-csrf-token","t3Wr3OoS-yxSbVl0HgKoo9IZOqN_9M420ukA");
            map.put("cookie","cna=Ke+6GmNYh0oCAbaWG2j8UfOE; aliyun_lang=zh; aliyun_choice=CN; x-hng=lang=zh-CN; t=dd3f8658e5691e73b3729a52c851edeb; AONE_SESSION=0a263f1e-ff15-4b89-88e8-0b4405719351; _samesite_flag_=true; cookie2=17815363a1a158e44188b48f497ceb2b; _tb_token_=ea3e1ee7736de; _hvn_login=6; login_aliyunid_pk=1693645411130876; ak_user_locale=zh_CN; ak_user_locale=zh_CN; TB_ACCESS_TOKEN=eyJhbGciOiJFZERTQSIsInR5cCI6IkpXVCJ9.eyJhcHAiOiI1ZTczNzAyMjNiYjY5MzczNjYwNjAxYTQiLCJhdWQiOiIiLCJleHAiOjE2NDk5MDMzODksImlhdCI6MTY0OTY0NDE4OSwiaXNzIjoidHdzIiwianRpIjoid1dQSTdHN2pDczM0dEN2Q29FekFfRnQzRmR0RW83Z3U0dTJyaVFRZGhITT0iLCJyZW5ld2VkIjoxNjQ5MzA2MjI3NjY4LCJzY3AiOlsiYXBwX3VzZXIiXSwic3ViIjoiNjIxNTkyMTg4YWY1ZDM5ZWIzMzA2OGM1IiwidHlwIjoiYWNjZXNzX3Rva2VuIn0.qBkTXCbbZDs5zz4Tj0PhAGND8JCIL8x6UkND65Ih5DDzJdcdy-NWkzNMKB7b-bP0gDjheU-x_Ge-U3F1IUzMCg; XSRF-TOKEN=91619ea9-465f-4db3-8354-8a3e851f80aa; TB_ACCESS_TOKEN=eyJhbGciOiJFZERTQSIsInR5cCI6IkpXVCJ9.eyJhcHAiOiI2MDM4NmU0N2MyNzFmZTA5YjRiZmE2NTgiLCJhdWQiOiIiLCJleHAiOjE2NDk5MDM2ODUsImlhdCI6MTY0OTY0NDQ4NSwiaXNzIjoidHdzIiwianRpIjoibURtczBBLWtHZ0RzMm5ETlQwVDY1dkhjQnRMTVRGQVZwZERVNG1ZODNUMD0iLCJyZW5ld2VkIjoxNjQ5MzA2MjI3NjY4LCJzY3AiOlsiYXBwX3VzZXIiXSwic3ViIjoiNjIxNTkyMTg4YWY1ZDM5ZWIzMzA2OGM1IiwidHlwIjoiYWNjZXNzX3Rva2VuIn0.PSBwdgmi1_wfbZdAW-e_sL1yhuqU4-9J_4zlzXj_Dt3k80B5AS301xoql7BsW58UIutJtjDjOcC6EZyGfSwiCA; csg=a3ba43e5; login_aliyunid=\"dingtalk_jy****\"; login_aliyunid_ticket=mXnKxIot5SHKXLgL1MncD5hZLZU0cqPtA7mt_oFpof_BNTwUhTOoNC1ZBeeMfKJzxdnb95hYssNIZor6q7SCxRtgmGCbifG2Cd4ZWazmBdHI6sgXZqg4XFWQfyKpeu*0vCmV8s*MT5tJl3_1$$wlbKP10; login_aliyunid_csrf=_csrf_tk_1671349652300948; hssid=1gf_Tlz4JeUeadWNx3uaCdw1; hsite=6; aliyun_country=CN; aliyun_site=CN; LOGIN_ALIYUN_PK_FOR_TB=1693645411130876; TEAMBITION_SESSIONID=eyJ1aWQiOiI2MjE1OTIxODhhZjVkMzllYjMzMDY4YzUiLCJhdXRoVXBkYXRlZCI6MTY0OTMwNjIyNzY2OCwidXNlciI6eyJfaWQiOiI2MjE1OTIxODhhZjVkMzllYjMzMDY4YzUiLCJuYW1lIjoi6Iqx6I2jIiwiZW1haWwiOiJhY2NvdW50c182MjE1OTIxODk3M2ZjNzAwMmQyOGQ4ZDBAbWFpbC50ZWFtYml0aW9uLmNvbSIsImF2YXRhclVybCI6Imh0dHBzOi8vdGNzLWRldm9wcy5hbGl5dW5jcy5jb20vdGh1bWJuYWlsLzExMmYyMmE3MDQ4MWYyMDU5YTEyOGM3Y2JiNDAzMmJmNjQwZS93LzEwMC9oLzEwMCIsInJlZ2lvbiI6InVzIiwibGFuZyI6IiIsImlzUm9ib3QiOmZhbHNlLCJvcGVuSWQiOiIiLCJwaG9uZUZvckxvZ2luIjoiIiwiY3JlYXRlZCI6IjIwMjItMDItMjNUMDE6NDc6MDQuMjA3WiJ9LCJsb2dpbkZyb20iOiIifQ==; TEAMBITION_SESSIONID.sig=aZ2ABkhl8HLUuZYfAaRgjym8M2Q; isg=BA0NWAa8U7-JgPf3W2YthLSyHCmH6kG8sBX66U-SUKQTRi34FzqFjFjXtNoggll0; l=eBImwuT7LMW8NSsJBOfaourza77T_IRYmuPzaNbMiOCPOi5B5eOOW62DcIY6CnGVh6uyR3lrOrBwBeYBqQd-nxv96aiE7mDmn; tfstk=c-APB7wgNbhP341IB_1EAJ5_PbYRZaxJvl_GEcxb3qRZFwWliRqdocoCCTU4xTf..; acw_tc=c0a8050116496523641852156e1b89c1c2c4b202d165279309154b2c7b59cd");


            while ((s = br.readLine()) != null) {
                //result.append(System.lineSeparator() + s);
               String temo=url+s;
                HttpClientUtils.doGet(temo,map);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }




}

