/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : huarong
 * @version 1: SprintBugsTotalBo.java, v 0.1 2022-05-24 10:14 lixiaoguang
 */

@Getter
@Setter
public class SprintBugsInfoBo {
    private  int  bugTotal;
    private  int todayBugNum;

    private List<KeyWordVauleBo> assignedToList;
    private List<KeyWordVauleBo> chengduList;
    private List<KeyWordVauleBo> typeList;
    private List<KeyWordVauleBo> statusList;
    private List<KeyWordVauleBo> daysNumList;
    private List<KeyWordVauleBo> deptNumList;

}