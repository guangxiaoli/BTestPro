/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.dy.cdqa.btest.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author : huarong
 * @version 1: FileOprUtils.java, v 0.1 2022-03-30 20:04 lixiaoguang
 */
public class FileOprUtils {


    public static String fileRead(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();

    }

}