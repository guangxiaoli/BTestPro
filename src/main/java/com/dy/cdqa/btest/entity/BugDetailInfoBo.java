/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : huarong
 * @version 1: BugDetailInfoDaoBO.java, v 0.1 2022-04-24 16:54 lixiaoguang
 */

@Getter
@Setter
public class BugDetailInfoBo {
    private   int  id;
    private   String  bugid;
    private   String  sprintid;
    private   String  proid;
    private   String  assignedTo;
    private   String  type;
    private   String  bugstatus;
    private   String  chengdu;
    private  String  virtualDept;
    private   String  createday;
    private  boolean checkToday;
    private  long gmtCreate;

}