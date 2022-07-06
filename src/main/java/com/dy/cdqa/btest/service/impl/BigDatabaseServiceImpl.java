/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.service.impl;

import com.aliyun.devops20210625.Client;
import com.aliyun.devops20210625.models.*;
import com.dy.cdqa.btest.entity.*;
import com.dy.cdqa.btest.mapper.DyBtestBigMapper;
import com.dy.cdqa.btest.service.BigDatabaseService;
import com.dy.cdqa.btest.utils.YunXiaoInfoEnum;
import com.dy.cdqa.btest.utils.YunxiaoUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : huarong
 * @version 1: BigDatabaseServiceImpl.java, v 0.1 2022-04-24 17:03 lixiaoguang
 */


@Service("bigDatabaseService")
public class BigDatabaseServiceImpl implements BigDatabaseService {

    @Resource
    DyBtestBigMapper dyBtestBigMapper;
    Client SDKClient;

    @Override
    public List<BugDetailInfoBo> bugList(String sprintId) {
        List<BugDetailInfoBo> bugList = dyBtestBigMapper.getBugListBySprintId(sprintId);
        return bugList;
    }

    @Override
    public int delebugsBySprintId(String sprintId) {
        return dyBtestBigMapper.deleDataBySprintId(sprintId);
    }

    @Override
    public int delebugsByProId(String proId,boolean isAir) {
        if(isAir){
            return dyBtestBigMapper.deleAirDataByProId(proId);
        }else{
            return  dyBtestBigMapper.deleBigDataByProId(proId);
        }
    }

    @Override
    public int inertBigBugInfo(List<BugDetailInfoBo> bo) {
        return dyBtestBigMapper.addBugsData(bo);

    }

    @Override
    public int deleTaskAndReqData(String proId,String type) {
       return dyBtestBigMapper.deleTaskAndReq(proId,type);
    }

    @Override
    public int addTaskAndReqData(List<TaskAndReqBo> list) {
        return dyBtestBigMapper.insertTaskAndReq(list);
    }

    @Override
    public void updateAllProAndSprints() {
       List<ProjectInfoBo>  proList =new ArrayList<>();
        try {
            if (SDKClient == null) {
                SDKClient = YunxiaoUtils.createClient();
            }
            ListProjectsRequest listProjectsRequest = new ListProjectsRequest().setCategory("Project");
            List<ListProjectsResponseBody.ListProjectsResponseBodyProjects> list=SDKClient.listProjects(YunXiaoInfoEnum.ORGID.getCode(), listProjectsRequest).getBody().getProjects();
            for (ListProjectsResponseBody.ListProjectsResponseBodyProjects item:list){
                List<ProjectInfoBo>  temList= sprintListByProID(item.getIdentifier(),item.getName());
                if(temList.size()>0){
                    proList.addAll(temList);
                }
            }
            if(proList.size()>0){
                int t=dyBtestBigMapper.deleAllProInfo();
                if(t>0){
                    dyBtestBigMapper.addProAndSprintInfo(proList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public  int addAutoResult(AutoTestResultBo result){
        Date date=new Date(System.currentTimeMillis());
        String P_ymd = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sf = new SimpleDateFormat(P_ymd);
        String timeStr = sf.format(date);
        result.setCreateTime(timeStr);
         return  dyBtestBigMapper.addAutoTestResult(result);
    }

    @Override
    public List<KeyWordVauleBo> bugsCountInfo(String keyWord,String sprintid) {
        return dyBtestBigMapper.bugsNumberGroupByKey(keyWord,sprintid);
    }

    @Override
    public  List<AssignInfoBo>  getAssignDept(){
        return  dyBtestBigMapper.allAssingInfo();
    }

    @Override
    public List<AutoTestResultBo> getTestResInfo() {
        List<AutoTestResultBo>  resList=new ArrayList<>();
        List<AutoTestResultBo> autoTestResultBos = dyBtestBigMapper.allAutoTestRes();
        NumberFormat format=NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        for(AutoTestResultBo bo :autoTestResultBos){
            double  number= bo.getSuccCount() * 1.0;
            double  total=bo.getTotalCount() * 1.0;
            String point= format.format(number/total);
            if(bo.getAutoType()==1){
                bo.setTypeDesc("接口测试");
            }else{
                bo.setTypeDesc("其他类型");
            }
            bo.setSuccPoint(point);
            resList.add(bo);
        }
        return resList;
    }

    @Override
    public int insertAirBugsList(List<AirBugInfoBo> list) {
        return dyBtestBigMapper.addAirBugsInfo(list);

    }

    @Override
    public List<ProjectInfoBo> proList(){
        return  dyBtestBigMapper.proInfoList();
    }


    @Override
    public List<ProjectInfoBo> sprintslistByProId(String proId){
        return   dyBtestBigMapper.sprintListByDBProId(proId);
    }


    /**
     * 通过项目ID 获取项目下所有的迭代
     * @param proId
     * @param proName
     * @return
     * @throws Exception
     */
    private   List<ProjectInfoBo> sprintListByProID(String proId,String proName) throws Exception{
        if (SDKClient == null) {
            SDKClient = YunxiaoUtils.createClient();
        }
        List<ProjectInfoBo>  proList =new ArrayList<>();
        ListSprintsRequest sprintsRequest = new ListSprintsRequest();
        sprintsRequest.setSpaceType("Project");
        sprintsRequest.setSpaceIdentifier(proId);
        ListSprintsResponse listSprintsResponse = SDKClient.listSprints(YunXiaoInfoEnum.ORGID.getCode(), sprintsRequest);
        List<ListSprintsResponseBody.ListSprintsResponseBodySprints> sprints = listSprintsResponse.getBody().getSprints();
        if(sprints.size()<=0){
            return proList;
        }
        for(ListSprintsResponseBody.ListSprintsResponseBodySprints item:sprints){
            ProjectInfoBo  pro=new ProjectInfoBo();
            pro.setProId(item.getSpaceIdentifier());
            pro.setProName(proName);
            pro.setSprintId(item.getIdentifier());
            pro.setSprintName(item.getName());
            proList.add(pro);
        }
        return proList;
    }


}