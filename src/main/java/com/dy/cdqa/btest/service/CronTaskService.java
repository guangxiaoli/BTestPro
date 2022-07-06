/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.service;

/**
 * @author : huarong
 * @version 1: CronTaskService.java, v 0.1 2022-06-20 10:24 lixiaoguang
 */
public interface CronTaskService {
    /**
     * 获取所有的任务信息
     * @param proId 项目ID
     */
    public  void getTaskData(String proId,String type);

}