/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.utils;

/**
 * @author : huarong
 * @version 1: AirBugCustomFieldsEnum.java, v 0.1 2022-06-01 18:06 lixiaoguang
 */
public enum AirBugCustomFieldsEnum {

    /**
     *Bug类型
     */
    BugType("5854887762875e65073555fdc9","Bug类型"),

    /**
     *Bug优先级
     */
    BugLevel("priority","Bug优先级"),

    /**
     *Bug程度
     */
    BugDegree("seriousLevel","Bug程度"),

    /**
     * Bug所属客户
     */
    BugCustomer("87ca58a7baa70e8560ff9db518","Bug所属客户"),

    /**
     * Bug发现阶段
     */
    BugFindpath("873aeadab298fc07dd696cf08c","Bug发现阶段"),
    /**
     * Bug原因
     */
    BugReason("1375c5b504a791675e070574ed","Bug原因"),
    /**
     * 应用名称
     */
    SystemName("f0597302eb03db517674fe1d1c","应用名称"),
    /**
     * FeatureName
     */
    FeatureName("e2e3662022a2f812c175da766f","FeatureName"),

    /**
     * bug发现的迭代
     */
    BugfindSprint("b30c2f3ffd4a69ccb11d34fd9d","bug发现的迭代"),
    /**
     * 修复的迭代
     */
    BugrepairSprint("32c5e1b2a1efbf3946c284e7f6","修复的迭代"),
    /**
     * 修复时间
     */
    BugrepairTime("4febd726daf25fe852db96b344","修复时间");

    private String code;
    private String  desc;


    private AirBugCustomFieldsEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public   String getCode(){
        return  code;
    }
}