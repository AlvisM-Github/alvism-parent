package com.alvism.util.common.excel;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Created by JiaMingChen on 2018/4/11.
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        HashMap<String, Object> beans = new HashMap<>();
        //beans.put("total", "7");
        List<Integer> details = new ArrayList<>();
        details.add(3);
        details.add(2);
        details.add(7);
        beans.put("details", details);
        File template = AlvisMExcel.getTemplate("jftj.xlsx");
        File out = new File("D:/jftj.xlsx");
        AlvisMExcel.exportExcel(template, out, beans);
        System.out.println("导出成功");
    }

}
