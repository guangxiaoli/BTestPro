/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.controller;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.devops20210625.models.ListSprintsResponseBody;
import com.dy.cdqa.btest.common.ApiResult;
import com.dy.cdqa.btest.entity.*;
import com.dy.cdqa.btest.service.BigDatabaseService;
import com.dy.cdqa.btest.service.YunxiaoService;
import com.dy.cdqa.btest.utils.YunXiaoInfoEnum;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author : huarong
 * @version 1: YunxiaoController.java, v 0.1 2022-04-22 09:24 lixiaoguang
 */


@RequestMapping("/yunxiao")
@RestController
@CrossOrigin
public class YunxiaoController {

    @Autowired
    YunxiaoService  yunxiaoService;
    @Autowired
    BigDatabaseService bigDatabaseService;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(YunxiaoController.class);

    @RequestMapping(value = "/sprintsList",method =RequestMethod.GET)
    public ApiResult sprintsList(@RequestParam(value="proId",required=false) String proId)  {
        logger.info("请求：sprintsList");
        //获取项目下对应的迭代信息、这里是实时模式、proId为空默认是Big项目
        if (null == proId) {
            proId = YunXiaoInfoEnum.bigProId.getCode();
        }
        List<ListSprintsResponseBody.ListSprintsResponseBodySprints> listSprintsResponseBodySprints = yunxiaoService.listSprintsByProId(proId);
        return ApiResult.success(listSprintsResponseBodySprints,"success");
    }


    @RequestMapping(value = "/busInfoBySprintId",method =RequestMethod.GET)
    public ApiResult busInfoBySprintId(@RequestParam(value="sprintId",required=true) String sprintId)  {
        logger.info("请求：busInfoBySprintId");
        //通过迭代ID获取对应的bug数据信息、这里是查的库
        SprintBugsInfoBo bo = yunxiaoService.getBugsInfoByBySprintId(sprintId);
        return ApiResult.success(bo,"success");
    }


    @RequestMapping(value = "/createBugData",method =RequestMethod.GET)
    public ApiResult createBugData(@RequestParam(value="proId",required=true) String proId,int flag)  {
        logger.info("请求：createBugData");
        //手动触发-生成Air、Big项目下对应bug信息
        if(flag==1){
            yunxiaoService.createAllBugsData(proId,true);
        }else{
            yunxiaoService.createAllBugsData(proId,false);
        }
       return ApiResult.success("已执行生成数据命令","success");
    }


    @RequestMapping(value = "/proinfoList",method =RequestMethod.GET)
    public ApiResult proinfoList() throws Exception {
        //获取所有的项目Pro信息
        List<ProjectInfoBo> projectInfoBos = bigDatabaseService.proList();
        return ApiResult.success(projectInfoBos,"success");
    }

    @RequestMapping(value = "/sprintsListByDb",method =RequestMethod.GET)
    public ApiResult sprintsListByDb(@RequestParam(value="proId",required=false) String proId) throws Exception {
        //根据项目ID获取对应的迭代信息、这里是从数据库中获取
        List<ProjectInfoBo> projectInfoBos = bigDatabaseService.sprintslistByProId(proId);
        return ApiResult.success(projectInfoBos,"success");
    }

    @PostMapping(value = "/addAutoTestResult")
    public ApiResult addAutoTestResult(@RequestBody AutoTestResultBo result){
        int tem=bigDatabaseService.addAutoResult(result);
        if(tem>=1){
            return ApiResult.success("插入成功、id:"+tem);
        }
        return  ApiResult.failed("插入失败");
    }

    @RequestMapping(value = "/testResultList",method = RequestMethod.GET)
    public ApiResult testResultList(){
        //获取自动化结果的列表
        List<AutoTestResultBo> testResInfo = bigDatabaseService.getTestResInfo();
        return  ApiResult.success(testResInfo,"success");
    }


    @RequestMapping(value = "/updateproinfo",method =RequestMethod.GET)
    public ApiResult updateproinfo() throws Exception {
        //手动触发-更新公司下所有的项目和迭代信息
        bigDatabaseService.updateAllProAndSprints();
        return  ApiResult.success("已触发更新","success");
    }

    @RequestMapping(value = "/github",method =RequestMethod.GET)
    public ApiResult github() throws Exception {
        JSONObject  obj=new JSONObject();
        obj.put("name","花荣");
        obj.put("date","2022-07-06");
        obj.put("dept","质量组");
        return  ApiResult.success(obj,"success");
    }

}