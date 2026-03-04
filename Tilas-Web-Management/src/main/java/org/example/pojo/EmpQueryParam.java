package org.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    // 将条件查询中的若干参数打包成一个类
    private Integer page = 1; // 页码 // 这里设置的类成员默认值代替了此前写在方法参数中的注解@DefaultValue
    private Integer pageSize = 10; //每页展示记录数 // 这里设置的类成员默认值代替了此前写在方法参数中的注解@DefaultValue
    private String name; //姓名
    private Integer gender; //性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //入职开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //入职结束时间
}