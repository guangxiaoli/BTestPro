/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.service;

import com.dy.cdqa.btest.entity.*;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Map;

/**
 * @author : huarong
 * @version 1: BigDatabaseService.java, v 0.1 2022-04-24 17:03 lixiaoguang
 */
public interface BigDatabaseService {
    /**
     * 根据迭代ID 获取bug列表
     * @param sprintId 迭代id
     * @return
     */
    public List<BugDetailInfoBo> bugList(String sprintId);

    /**
     *根据迭代id删除bug信息
     * @param sprintId
     * @return
     */
    public int  delebugsBySprintId(String sprintId);

    /**
     * 根据项目proId 删除bug信息
     * @param proId
     * @return
     */
    public int  delebugsByProId(String proId,boolean isAir);



    /**
     * 获取所有的项目和跌迭代信息
     */
    public void  updateAllProAndSprints();


    /**
     *获取所有的项目信息-本地数据库
     * @return
     */
    public List<ProjectInfoBo> proList();

    /**
     * 获取所有的迭代信息-本地数据
     * @param proId
     * @return
     */
    public List<ProjectInfoBo> sprintslistByProId(String proId);

    /**
     * 插入自动化后的结果
     * @param result
     * @return
     */
    public  int addAutoResult(AutoTestResultBo result);

    /**
     * 统计数量
     * @param keyWord
     * @param sprintid
     * @return
     */
    public  List<KeyWordVauleBo>  bugsCountInfo(String keyWord,String sprintid);

    /**
     * 查询所有的人员获取部门信息
     * @return
     */
    public  List<AssignInfoBo>  getAssignDept();

    /**
     *获取自动化结果列表
     * @return
     */
    public  List<AutoTestResultBo> getTestResInfo();

    /**
     *插入air的bug信息
     * @param list
     * @return
     */
    public  int insertAirBugsList(List<AirBugInfoBo> list);

    /**
     * 插入bug信息  -这里是big使用
     * @param bo
     * @return
     */
    public  int  inertBigBugInfo( List<BugDetailInfoBo> bo);

    /**
     *
     * @param proId
     * @return
     */
    public  int  deleTaskAndReqData(String proId,String type);

    /**
     *
     * @param list
     * @return
     */
    public  int addTaskAndReqData(List<TaskAndReqBo> list);
}