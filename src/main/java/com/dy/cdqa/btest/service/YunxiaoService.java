/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.service;

import com.aliyun.devops20210625.models.GetWorkItemInfoResponseBody;
import com.aliyun.devops20210625.models.ListSprintsResponseBody;
import com.dy.cdqa.btest.entity.SprintBugsInfoBo;

import java.util.List;

/**
 * @author : huarong
 * @version 1: YunxiaoService1.java, v 0.1 2022-04-22 09:42 lixiaoguang
 */




public interface YunxiaoService {


    /**
     * 通过项目Id 获取项目下的迭代list、这里是实时模式
     * @param spaceIdentifier  项目ID
     * @return
     */
    public List<ListSprintsResponseBody.ListSprintsResponseBodySprints> listSprintsByProId(String spaceIdentifier);

    /**
     * 通过迭代获取所有的bug信息
     * @param SprintID
     * @return
     */
    public SprintBugsInfoBo getBugsInfoByBySprintId(String SprintID);


    /**
     * 根据迭项目ID生成 bug数据
     * @param proId
     */
    public void createAllBugsData(String proId,boolean isAirPro);


    /**
     * 获取Bug工作项的详细信息
     * @param workitemId  工作项ID 这里指的是BugID
     * @return
     * @throws Exception
     */
    public GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitem bugItemDetail(String workitemId) throws Exception;





}