/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.other;

import com.alibaba.fastjson.JSON;
import com.aliyun.devops20210625.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.dy.cdqa.btest.entity.AutoTestResultBo;

import java.util.List;

/**
 * @author : huarong
 * @version 1: codeTest.java, v 0.1 2022-04-15 16:21 lixiaoguang
 */
public class codeTest {

    public static void main(String[] args) {
        AutoTestResultBo bo=new AutoTestResultBo();
        bo.setAutoType(1);
        bo.setTotalCount(100);
        bo.setSuccCount(50);
        bo.setFailCount(500);
        bo.setCheckUrl("www.baidu.com");

        System.out.println(JSON.toJSONString(bo));
    }

}