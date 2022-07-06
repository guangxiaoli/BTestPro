/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author : huarong
 * @version 1: AutoTestResultBo.java, v 0.1 2022-05-19 16:34 lixiaoguang
 */

@Getter
@Setter
public class AutoTestResultBo {
    private  int id;
    @NotNull
    private int autoType;
    private String typeDesc;
    @NotNull
    private  int totalCount;
    @NotNull
    private  int succCount;
    private  int failCount;
    private  String checkUrl;
    private String  createTime;
    private  String  succPoint;
}