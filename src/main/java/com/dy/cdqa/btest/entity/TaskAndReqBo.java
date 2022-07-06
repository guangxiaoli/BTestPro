/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : huarong
 * @version 1: TaskAndReqBo.java, v 0.1 2022-06-20 10:11 lixiaoguang
 */

@Getter
@Setter
public class TaskAndReqBo {
    private  int id;
    private  String identifier;
    private  String categoryIdentifier;
    private  String subject;
    private  String sprintid;
    private  String sprintname;
    private  String proid;
    private  String proname;
    private  String assignedto;
    private  String itemstatus;

}