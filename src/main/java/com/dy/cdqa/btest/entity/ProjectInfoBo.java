/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : huarong
 * @version 1: ProjectInfoBo.java, v 0.1 2022-05-16 17:25 lixiaoguang
 */

@Getter
@Setter
public class ProjectInfoBo {
    private  String proId;
    private  String proName;
    private  String sprintId;
    private  String sprintName;
    private  boolean updateFlag;


}