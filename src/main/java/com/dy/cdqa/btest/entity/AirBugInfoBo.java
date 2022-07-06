/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author : huarong
 * @version 1: BugInfoDetailBo.java, v 0.1 2022-06-01 16:26 lixiaoguang
 */

@Getter
@Setter
public class AirBugInfoBo {
    private  int id;
    private  String bugid;
    private  String bugtitle;
    private  String sprintid;
    private  String sprintname;
    private  String proid;
    private  String proname;
    private  String assignedto;
    private  String creater;
    private  String createtime;
    private  String createday;
    private  String bugtype;
    private  String bugstatus;
    private  String buglevel;
    private  String bugdegree;
    private  String bugcustomer;
    private  String bugfindpath;
    private  String bugreason;
    private  String featurename;
    private  String systemname;
    private  String bugfindSprint;
    private  String bugrepairtime;
    private  String bugrepairsprint;


}