/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.common;

/**
 * @author : huarong
 * @version 1: IErrorCode.java, v 0.1 2022-04-22 09:33 lixiaoguang
 */
public interface IErrorCode {
    /**
     * 错误编码: -1失败;200成功
     *
     * @return 错误编码
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return 错误描述
     */
    String getMessage();
}
