/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.work;
import com.dy.cdqa.btest.service.BigDatabaseService;
import com.dy.cdqa.btest.service.CronTaskService;
import com.dy.cdqa.btest.service.YunxiaoService;
import com.dy.cdqa.btest.utils.YunXiaoInfoEnum;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/**
 * @author : huarong
 * @version 1: CronTaskBugsBySpringId.java, v 0.1 2022-04-24 17:19 lixiaoguang
 */

@Component
public class CronTaskGetDataWork {

    @Autowired
    YunxiaoService yunxiaoService;
    @Autowired
    BigDatabaseService bigDatabaseService;

    @Autowired
    CronTaskService cronTaskService;

   // private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CronTaskBuglistBySprintid.class);
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 定时更新-更新air
     */
    @Scheduled(cron = "0 0/13 * * * ?")
    public void doWorkForAir(){
        long start=System.currentTimeMillis();
        yunxiaoService.createAllBugsData(YunXiaoInfoEnum.airProId.getCode(),true);
        logger.info("AIR 耗时-->秒："+(System.currentTimeMillis()-start)/1000);

    }



    /**
     * 定时更新- 更新Big
     */
    @Scheduled(cron = "0 0/17 * * * ?")
    public void doWorkForBig(){
        long start=System.currentTimeMillis();
        yunxiaoService.createAllBugsData(YunXiaoInfoEnum.bigProId.getCode(), false);
        logger.info("BIG 耗时-->秒："+(System.currentTimeMillis()-start)/1000);
    }



    /**
     * 定时触发更新 - 将公司下所有的项目和迭代信息获取 并存入本地库
     */
   @Scheduled(cron = "0 0/59 * * * ?")
    public void autoUpdateProInfo(){
        bigDatabaseService.updateAllProAndSprints();
        logger.info("任务执行完成：将公司下所有的项目和迭代信息获取 并存入本地库");

    }



    /**
     * 定时触发更新 - 获取AIR项目下的所有任务数据
     */
    @Scheduled(cron = "0 0/25 * * * ?")
    public void getAirProTask(){
        cronTaskService.getTaskData(YunXiaoInfoEnum.airProId.getCode(),"Task");
        cronTaskService.getTaskData(YunXiaoInfoEnum.airProId.getCode(),"Req");


    }





}