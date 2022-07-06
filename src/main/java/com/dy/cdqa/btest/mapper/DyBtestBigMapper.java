/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.mapper;

import com.dy.cdqa.btest.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : huarong
 * @version 1: DyBtestBigMapper.java, v 0.1 2022-04-24 16:44 lixiaoguang
 */

@Mapper
public interface DyBtestBigMapper {
    /**
     *通过迭代ID 获取bug列表
     * @param sprintId  迭代ID
     * @return
     */
    public List<BugDetailInfoBo> getBugListBySprintId(@Param("sprintId") String sprintId);

    /**
     *通过迭代ID删除bug数据
     * @param sprintId
     * @return
     */
    public  int  deleDataBySprintId(@Param("sprintId") String sprintId);

    /**
     * 根据proId删除Air表中的数据
     * @param proId
     * @return
     */
    public  int deleAirDataByProId(@Param("proId") String proId);

    /**
     * 根据proId 删除big表中数据
     * @param proId
     * @return
     */
    public  int deleBigDataByProId(@Param("proId") String proId);




    /**
     *新增bug数据 -这里是给big用
     * @param bo
     * @return
     */
    public int  addBugsData(List<BugDetailInfoBo> bo);

    /**
     * 增加air的bug信息
     * @param list
     * @return
     */
    public int addAirBugsInfo(List<AirBugInfoBo> list);

    /**
     * 增加项目和对应的迭代信息
     * @param proList
     * @return
     */
    public  int addProAndSprintInfo( List<ProjectInfoBo>  proList);

    /**
     * 删除所有的项目和迭代信息
     * @return
     */
    public  int deleAllProInfo();


    /**
     *获取所有的项目信息
     * @return
     */
    public List<ProjectInfoBo> proInfoList();

    /**
     * 根据项目id查询迭代信息、本地库、非实时
     * @param proId
     * @return
     */
    public List<ProjectInfoBo> sprintListByDBProId(String proId);

    /**
     * 新增自动化测试结果
     * @param bo
     * @return
     */
    public int addAutoTestResult(AutoTestResultBo bo);


    /**
     * 统计关键字 GROUP BY 统计数量
     * @param keyWord
     * @param sprintid
     */
    public   List<KeyWordVauleBo>  bugsNumberGroupByKey(@Param("keyword") String keyWord,@Param("sprintid") String sprintid);

    /**
     * 查询所的人员获取 模块部门信息
     * @return
     */
    public  List<AssignInfoBo>  allAssingInfo();


    /**
     * 所有的信息
     * @return
     */
    public  List<AutoTestResultBo> allAutoTestRes();


    /**
     * 根据项目Id删除 task和 req
     * @param proId
     * @param type
     * @return
     */
    public  int deleTaskAndReq(String proId,String type);

    /**
     * 插入task 和 req
     * @param list
     * @return
     */
    public  int  insertTaskAndReq(List<TaskAndReqBo> list);



}