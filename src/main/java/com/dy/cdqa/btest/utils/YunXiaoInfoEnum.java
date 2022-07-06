/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.utils;

/**
 * @author : huarong
 * @version 1: YunXiaoInfoEnum.java, v 0.1 2022-04-22 11:39 lixiaoguang
 */
public enum YunXiaoInfoEnum {

    /**
     *企业ID
     */
    ORGID("60ddFDSFFF21","企业ID"),

    /**
     *accesskey
     */
    ACCESSKEY("60AAdFSFSDFSD021","accesskey"),
    /**
     *accessKeySecret
     */
    SECRET("60FSFSFFSFDFSF21","accessKeySecret"),
    bigProId("bFSFFFFFFF8a0","项目ID"),
    airProId("80c811111111a0","项目ID");

    private String code;
    private String  desc;


    private YunXiaoInfoEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public   String getCode(){
        return  code;
    }
}