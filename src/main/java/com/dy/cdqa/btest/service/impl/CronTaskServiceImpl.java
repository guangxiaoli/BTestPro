/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.service.impl;

import com.aliyun.devops20210625.Client;
import com.aliyun.devops20210625.models.GetOrganizationMemberResponse;
import com.aliyun.devops20210625.models.ListWorkitemsRequest;
import com.aliyun.devops20210625.models.ListWorkitemsResponse;
import com.aliyun.devops20210625.models.ListWorkitemsResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dy.cdqa.btest.entity.AirBugInfoBo;
import com.dy.cdqa.btest.entity.BugDetailInfoBo;
import com.dy.cdqa.btest.entity.ProjectInfoBo;
import com.dy.cdqa.btest.entity.TaskAndReqBo;
import com.dy.cdqa.btest.service.BigDatabaseService;
import com.dy.cdqa.btest.service.CronTaskService;
import com.dy.cdqa.btest.utils.YunXiaoInfoEnum;
import com.dy.cdqa.btest.utils.YunxiaoUtils;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : huarong
 * @version 1: CronTaskServiceImpl.java, v 0.1 2022-06-20 10:24 lixiaoguang
 */
@Service("cronTaskService")
public class CronTaskServiceImpl implements CronTaskService {
    @Resource
    private BigDatabaseService bigDatabaseService;

    private Client SDKClient;
    private Map<String,String> sprintMap=new HashMap<>();
    private Map<String, String> assignedToNameMap = new HashMap();


    @Override
    @Synchronized
    public void getTaskData(String proId,String type) {
        sprintMapFullValue(proId);
        int pageSize = 200;

        List<TaskAndReqBo> resList=new ArrayList<>();

        try{

            if (SDKClient == null) {
                SDKClient = YunxiaoUtils.createClient();
            }

            ListWorkitemsRequest listWorkitemsRequest = new ListWorkitemsRequest()
                    .setCategory(type)
                    .setSpaceIdentifier(proId)
                    .setSpaceType("Project")
                    .setMaxResults(Integer.toString(pageSize));
            RuntimeOptions runtime = new RuntimeOptions();
            java.util.Map<String, String> headers = new java.util.HashMap<>();
            ListWorkitemsResponse listWorkitemsResponse = SDKClient.listWorkitemsWithOptions(YunXiaoInfoEnum.ORGID.getCode(),listWorkitemsRequest,headers,runtime);
            ListWorkitemsResponseBody body = listWorkitemsResponse.getBody();

            int totalCount = body.getTotalCount().intValue();
            if (totalCount <= pageSize) {
                List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitems = body.getWorkitems();
                if (null == workitems || workitems.size() <= 0) { return;}
                resList.addAll(taskItemsDetail(workitems));
            } else {
                String tempNextToken = body.getNextToken();
                int num = (totalCount / pageSize) + 1;
                for (int i = 0; i < num; i++) {
                    listWorkitemsRequest.setNextToken(tempNextToken);
                    ListWorkitemsResponse listWorkitemsRes = SDKClient.listWorkitemsWithOptions(YunXiaoInfoEnum.ORGID.getCode(),listWorkitemsRequest,headers,runtime);
                    List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitems = listWorkitemsRes.getBody().getWorkitems();
                    tempNextToken = listWorkitemsRes.getBody().getNextToken();
                    if (null == workitems || workitems.size() <= 0) { continue;}
                    resList.addAll(taskItemsDetail(workitems));
                }
            }


            if(resList.size()>0){
                bigDatabaseService.deleTaskAndReqData(proId,type);
                bigDatabaseService.addTaskAndReqData(resList);
            }




        }catch (Exception e){
            e.printStackTrace();
        }



    }





    private  List<TaskAndReqBo> taskItemsDetail(List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitems) throws  Exception{
        List<TaskAndReqBo> list=new ArrayList<>();
        for(ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems item:workitems ){
            TaskAndReqBo  bo=new TaskAndReqBo();
            bo.setIdentifier(item.getIdentifier());
            bo.setProid(item.getSpaceIdentifier());
            bo.setProname(item.getSpaceName());
            bo.setAssignedto(assignedToName(item.getAssignedTo()));
            bo.setSubject(item.getSubject());
            bo.setItemstatus(item.getStatus());
            bo.setCategoryIdentifier(item.getCategoryIdentifier());

            if(null != item.getSprintIdentifier()){
                bo.setSprintid(item.getSprintIdentifier());
                if(sprintMap.containsKey(item.getSprintIdentifier())){
                    bo.setSprintname(sprintMap.get(item.getSprintIdentifier()));
                }
            }
            list.add(bo);
        }
        return  list;
    }



    private void  sprintMapFullValue(String proid){
        List<ProjectInfoBo> sprintsList = bigDatabaseService.sprintslistByProId(proid);
        if(sprintsList.size()<=0){
            return;
        }
        for(ProjectInfoBo bo :sprintsList){
            sprintMap.put(bo.getSprintId(),bo.getSprintName());
        }
    }


    private String assignedToName(String accountId) throws Exception {
        if (null == SDKClient) {
            SDKClient = YunxiaoUtils.createClient();
        }
        if (assignedToNameMap.containsKey(accountId)) {
            return assignedToNameMap.get(accountId);
        }
        GetOrganizationMemberResponse organizationMember = SDKClient.getOrganizationMember(YunXiaoInfoEnum.ORGID.getCode(), accountId);
        String name = organizationMember.getBody().getMember().getOrganizationMemberName();
        assignedToNameMap.put(accountId, name);
        return name;
    }

}