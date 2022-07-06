/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.utils;

import com.aliyun.teaopenapi.models.Config;

/**
 * @author : huarong
 * @version 1: YunxiaoUtils.java, v 0.1 2022-04-22 09:49 lixiaoguang
 */
public class YunxiaoUtils {
    private  static  final String accessKeyId="LFSFFDFFFFFFFEM";
    private  static  final String accessKeySecret="utoFFFFFFFFFF0";
    private  static  final String endpointUrl="devops.cn-hangzhou.aliyuncs.com";

    public static com.aliyun.devops20210625.Client createClient() throws Exception {

        Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(accessKeySecret);
        config.endpoint = endpointUrl;
        return new com.aliyun.devops20210625.Client(config);
    }

}