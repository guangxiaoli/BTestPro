/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.service.impl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.devops20210625.Client;
import com.aliyun.devops20210625.models.*;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dy.cdqa.btest.entity.*;
import com.dy.cdqa.btest.service.BigDatabaseService;
import com.dy.cdqa.btest.service.YunxiaoService;
import com.dy.cdqa.btest.utils.AirBugCustomFieldsEnum;
import com.dy.cdqa.btest.utils.YunXiaoInfoEnum;
import com.dy.cdqa.btest.utils.YunxiaoUtils;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author : huarong
 * @version 1: YunxiaoServiceImpl.java, v 0.1 2022-04-22 09:42 lixiaoguang
 */

@Service("yunxiaoService")
public class YunxiaoServiceImpl implements YunxiaoService {

    @Resource
    private BigDatabaseService bigDatabaseService;
    private Client SDKClient;
    private Map<String,String> deptMap=new HashMap<>();
    private Map<String,String> sprintMap=new HashMap<>();
    private Map<String, String> assignedToNameMap = new HashMap();



    @Override
    public List<ListSprintsResponseBody.ListSprintsResponseBodySprints> listSprintsByProId(String spaceIdentifier) {
        List<ListSprintsResponseBody.ListSprintsResponseBodySprints> sprintList = new ArrayList<ListSprintsResponseBody.ListSprintsResponseBodySprints>();
        try {
            if (null == SDKClient) {
                SDKClient = YunxiaoUtils.createClient();
            }
            ListSprintsRequest sprintsRequest = new ListSprintsRequest();
            sprintsRequest.setSpaceType("Project");
            sprintsRequest.setSpaceIdentifier(spaceIdentifier);
            ListSprintsResponse listSprintsResponse = SDKClient.listSprints(YunXiaoInfoEnum.ORGID.getCode(), sprintsRequest);
            sprintList = listSprintsResponse.getBody().getSprints();
            return sprintList;
            } catch (Exception e) {
            e.printStackTrace();
            }
        return sprintList;
    }




    @Override
    public SprintBugsInfoBo getBugsInfoByBySprintId(String SprintID) {

        List<BugDetailInfoBo> bugsList = bigDatabaseService.bugList(SprintID);
        int bugTotalNum = bugsList.size();
        int todayBugNum = 0;
        for(BugDetailInfoBo tmp:bugsList){
            if(tmp.isCheckToday()){
                todayBugNum++;
            }
        }
        SprintBugsInfoBo bo=new SprintBugsInfoBo();

        bo.setAssignedToList(addPercentPoint(bugTotalNum,"assignedTo",SprintID));
        bo.setStatusList(addPercentPoint(bugTotalNum,"bugstatus",SprintID));
        bo.setChengduList(addPercentPoint(bugTotalNum,"chengdu",SprintID));
        bo.setDeptNumList(addPercentPoint(bugTotalNum,"virtualDept",SprintID));
        //给日期排序
        List<KeyWordVauleBo> createdayList = addPercentPoint(bugTotalNum, "createday", SprintID);
        createdayList.sort(Comparator.comparing(KeyWordVauleBo::getKeyWord));
        bo.setDaysNumList(createdayList);

        // 给type排序- 这里不排序也可以
        List<KeyWordVauleBo> typeList = addPercentPoint(bugTotalNum, "type", SprintID);
        typeList.sort(Comparator.comparing(KeyWordVauleBo::getKeyWord));
        bo.setTypeList(typeList);


        bo.setTodayBugNum(todayBugNum);
        bo.setBugTotal(bugTotalNum);
        return bo;
    }


    @Override
    @Synchronized
    public void createAllBugsData(String proId,boolean isAirPro) {
        int pageSize = 200;

        List<AirBugInfoBo> airBugsInfoList = new ArrayList<>();
        List<BugDetailInfoBo> bigBugsInfoList=new ArrayList<>();

        RuntimeOptions runtime = new RuntimeOptions();
        java.util.Map<String, String> headers = new java.util.HashMap<>();

        try {
            if (SDKClient == null) {
                SDKClient = YunxiaoUtils.createClient();
            }

            ListWorkitemsRequest listWorkitemsRequest = new ListWorkitemsRequest()
                    .setCategory("Bug")
                    .setSpaceIdentifier(proId)
                    .setSpaceType("Project")
                    .setMaxResults(Integer.toString(pageSize));
                   // .setConditions(JSON.toJSONString(ConditionsReqData(SprintId)));

            ListWorkitemsResponse listWorkitemsResponse = SDKClient.listWorkitemsWithOptions(YunXiaoInfoEnum.ORGID.getCode(),listWorkitemsRequest,headers,runtime);
            ListWorkitemsResponseBody body = listWorkitemsResponse.getBody();

            int totalCount = body.getTotalCount().intValue();
            // 如果返回的总数小于 pageSize、
            if (totalCount <= pageSize) {
                List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitems = body.getWorkitems();
                if (null == workitems || workitems.size() <= 0) { return; }
                if(isAirPro){
                    List<AirBugInfoBo> airBugsList = this.airBugsDetailList(workitems, proId);
                    airBugsInfoList.addAll(airBugsList);
                }else{
                    List<BugDetailInfoBo> bigBugsList = this.bigBugsDetailList(workitems);
                    bigBugsInfoList.addAll(bigBugsList);
                }

            } else {

                String tempNextToken = body.getNextToken();
                int num = (totalCount / pageSize) + 1;
                for (int i = 0; i < num; i++) {
                    listWorkitemsRequest.setNextToken(tempNextToken);
                    ListWorkitemsResponse listWorkitemsRes = SDKClient.listWorkitemsWithOptions(YunXiaoInfoEnum.ORGID.getCode(),listWorkitemsRequest,headers,runtime);
                    List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitems = listWorkitemsRes.getBody().getWorkitems();
                    if (null == workitems || workitems.size() <= 0) { continue;}
                    tempNextToken = listWorkitemsRes.getBody().getNextToken();
                    if(isAirPro){
                        List<AirBugInfoBo> airBugsList = this.airBugsDetailList(workitems, proId);
                        airBugsInfoList.addAll(airBugsList);
                    }else{
                        List<BugDetailInfoBo> bigBugsList = this.bigBugsDetailList(workitems);
                        bigBugsInfoList.addAll(bigBugsList);
                    }
                }
            }


            if (airBugsInfoList.size() > 0) {
                bigDatabaseService.delebugsByProId(proId,true);
                bigDatabaseService.insertAirBugsList(airBugsInfoList);
            }
            if(bigBugsInfoList.size()>0){
                bigDatabaseService.delebugsByProId(proId,false);
                bigDatabaseService.inertBigBugInfo(bigBugsInfoList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 处理返回数据中的list、拿到每个bug的信息
     * @param workitemsList bugList
     * @return
     * @throws Exception
     */
    private List<BugDetailInfoBo> bigBugsDetailList(List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitemsList) throws Exception {
        List<BugDetailInfoBo> bugInfoList = new ArrayList<>();
        for (ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems item : workitemsList) {
            BugDetailInfoBo bo = new BugDetailInfoBo();
            GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitem bugInfo = bugItemDetail(item.getIdentifier());
            String name = assignedToName(bugInfo.getAssignedTo());
            List<GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields> customFields = bugInfo.getCustomFields();
            String chengdu=customValue(customFields,"seriousLevel");
            // 取自定义字段的值
            String type=customValue(customFields,"5854887762875e65073555fdc9");
            bo.setProid(YunXiaoInfoEnum.bigProId.getCode());
            List<String> list=bugInfo.getSprint();
            if((null!=list) && (list.size()>0)){
                bo.setSprintid(bugInfo.getSprint().get(0));
            }
            bo.setBugid(bugInfo.getIdentifier());
            bo.setVirtualDept(assignedToDept(name));
            bo.setCreateday(getDateStr(bugInfo.getGmtCreate(),false));
            bo.setCheckToday(isToday(bugInfo.getGmtCreate()));
            bo.setBugstatus(bugInfo.getStatus());
            bo.setChengdu(chengdu);
            bo.setType(type);
            bo.setAssignedTo(name);
            bo.setGmtCreate(bugInfo.getGmtCreate());


            bugInfoList.add(bo);
        }

        return bugInfoList;
    }


    private List<AirBugInfoBo> airBugsDetailList(List<ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems> workitemsList,String proId) throws Exception{
        List<AirBugInfoBo> bugList=new ArrayList<>();
        sprintMapFullValue(proId);
        for (ListWorkitemsResponseBody.ListWorkitemsResponseBodyWorkitems item : workitemsList){
            AirBugInfoBo  airBug=new AirBugInfoBo();
            airBug.setBugid(item.getIdentifier());
            airBug.setBugtitle(item.getSubject());

            airBug.setAssignedto(assignedToName(item.getAssignedTo()));
            airBug.setBugstatus(item.getStatus());
            airBug.setProid(item.getSpaceIdentifier());
            airBug.setProname(item.getSpaceName());
            airBug.setCreater(assignedToName(item.getCreator()));
            airBug.setCreatetime(getDateStr(item.getGmtCreate(),true));
            airBug.setCreateday(getDateStr(item.getGmtCreate(),false));

            if(null!=item.getSprintIdentifier()){
                airBug.setSprintid(item.getSprintIdentifier());
                if(sprintMap.containsKey(item.getSprintIdentifier())){
                    airBug.setSprintname(sprintMap.get(item.getSprintIdentifier()));
                }
            }

            GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitem bugdetail = bugItemDetail(item.getIdentifier());
            List<GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields> customFieldsList = bugdetail.getCustomFields();
            airBug=customFieldsValue(customFieldsList,airBug);
            bugList.add(airBug);

        }
        return bugList;
    }




    private  AirBugInfoBo customFieldsValue(List<GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields> customFieldsList, AirBugInfoBo  airBug){
        for(GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields customFields:customFieldsList){
            String temp=customFields.getFieldIdentifier();
            if(temp.equals(AirBugCustomFieldsEnum.BugType.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugtype(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugLevel.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBuglevel(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugDegree.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugdegree(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugCustomer.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugcustomer(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugFindpath.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugfindpath(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugReason.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugreason(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.SystemName.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setSystemname(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.FeatureName.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setFeaturename(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugrepairSprint.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugrepairsprint(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugrepairTime.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugrepairtime(valueStr);
            }else if(temp.equals(AirBugCustomFieldsEnum.BugfindSprint.getCode())){
                String valueStr=checkCustomFields(customFields);
                airBug.setBugfindSprint(valueStr);
            }
        }
         return  airBug;
    }




    @Override
    public GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitem bugItemDetail(String workitemId) throws Exception {
        if (SDKClient == null) {
            SDKClient = YunxiaoUtils.createClient();
        }
        GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitem workitem = SDKClient.getWorkItemInfo(YunXiaoInfoEnum.ORGID.getCode(), workitemId).getBody().getWorkitem();
        return workitem;
    }



    /**
     * 将时间戳 转为字符格式 yyyy-MM-dd
     * @param createTime 时间戳
     * @return
     */
    private static String getDateStr(long createTime,boolean all) {
        String P_ymd = "yyyy-MM-dd";
        if(all){
            P_ymd = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sf = new SimpleDateFormat(P_ymd);
        String timeStr = sf.format(createTime);
        return timeStr;
    }


    /**
     * 判断时间是否为今天
     * @param createTime
     * @return
     */
    private static boolean isToday(Long createTime) {
        String P_ymd = "yyyy-MM-dd";
        SimpleDateFormat sf = new SimpleDateFormat(P_ymd);
        Date now = new Date();
        String nowStr = sf.format(now);
        String timeStr = sf.format(createTime);
        return timeStr.equals(nowStr);
    }

    /**
     * 获取对应人员的name
     * @param accountId
     * @return
     * @throws Exception
     */
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


    /**
     * 根据人员名称返回指定的部门
     * @param name
     * @return
     */
    private String assignedToDept(String name){
        if(deptMap.size()<=0){
            List<AssignInfoBo> list=bigDatabaseService.getAssignDept();
            for(AssignInfoBo bo:list){
                deptMap.put(bo.getAssignName(),bo.getVirtualDept());
            }
        }
        if(deptMap.containsKey(name)){
            return deptMap.get(name);
        }
        return  "其他";
    }

    /**
     * 生成conditions检索条件
     * -- 20220602更新：目前根据ProID生成数据，此方法暂不使用
     * @param SprintID
     * @return
     */
    private JSONObject ConditionsReqData(String SprintID) {
        JSONObject conditionGroups = new JSONObject();
        ReqBugNumBo requestBo = new ReqBugNumBo();
        String[] sid = new String[]{SprintID};
        requestBo.setValue(sid);
        JSONArray conditionGroupsValue = new JSONArray();
        conditionGroupsValue.add(requestBo);
        JSONArray temp = new JSONArray();
        temp.add(conditionGroupsValue);
        conditionGroups.put("conditionGroups", temp);
        return conditionGroups;
    }


    /**
     * 增减百分比数据
     * @param totalNum
     * @param key
     * @param sprintid
     * @return
     */
    private List<KeyWordVauleBo> addPercentPoint(int totalNum,String key,String sprintid){
        List<KeyWordVauleBo>   percentRes=new ArrayList<>();
        List<KeyWordVauleBo> tempList= bigDatabaseService.bugsCountInfo(key, sprintid);
        NumberFormat format=NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        for(KeyWordVauleBo bo:tempList){
            double  number= bo.getNumber() * 1.0;
            double  total=totalNum * 1.0;
            String point= format.format(number/total);
            bo.setPoint(point);
            percentRes.add(bo);
        }
        return  percentRes;
    }

    /**
     * map 排序
     * @param dayMap
     * @return
     */
    private static Map<String, Integer> mapSort(Map<String, Integer> dayMap) {
        Set<String> keys = dayMap.keySet();
        List<String> list = new ArrayList<>();
        list.addAll(keys);
        Collections.sort(list);
        // 设置为有序的map
        Map<String, Integer> newDayMap = new LinkedHashMap<>();
        for (String key : list) {
            newDayMap.put(key, dayMap.get(key));
        }
        return newDayMap;
    }

    /**
     *
     * @param customFields
     * @param key
     * @return
     */
    private String customValue(List<GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields> customFields,String key){
        for(GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields customfield:customFields){
            if(customfield.getFieldIdentifier().equals(key)){
               return customfield.getValueList().get(0).getValue();
            }
        }
        return "获取有误";
    }

    /**
     * 填充sprintMap、格式跌打ID、迭代name、供单个bug信息取值
     * @param proid
     */
    private void  sprintMapFullValue(String proid){
        List<ProjectInfoBo> sprintsList = bigDatabaseService.sprintslistByProId(proid);
        if(sprintsList.size()<=0){
            return;
        }
        for(ProjectInfoBo bo :sprintsList){
            sprintMap.put(bo.getSprintId(),bo.getSprintName());
        }
    }


    /**
     * 处理自定义字段ValueList为空的情况
     * @param customFields
     * @return
     */
    private  String checkCustomFields(GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFields customFields){
        List<GetWorkItemInfoResponseBody.GetWorkItemInfoResponseBodyWorkitemCustomFieldsValueList>list= customFields.getValueList();
        if((null!=list)&&(list.size()>0)){
            String valueStr=customFields.getValueList().get(0).getValue();
           return valueStr;
        }
        return "";
    }

}