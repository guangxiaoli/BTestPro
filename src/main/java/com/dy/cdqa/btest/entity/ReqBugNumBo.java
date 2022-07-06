/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * @author : huarong
 * @version 1: ReqBugNumBo.java, v 0.1 2022-04-15 17:37 lixiaoguang
 */


@Getter
@Setter
public class ReqBugNumBo {
    private  String fieldIdentifier="sprint";
    private  String operator="CONTAINS";
    private  String[] value;
    private  String toValue;
    private  String className="sprint";
    private  String format="list";

}