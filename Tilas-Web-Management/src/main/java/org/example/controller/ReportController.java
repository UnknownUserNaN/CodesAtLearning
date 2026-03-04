package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("生成员工工龄分布图");
        return Result.success(reportService.getEmpJobData());
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("生成员工性别分布图");
        return Result.success(reportService.getEmpGenderData());
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("生成学生学历分布图");
        return Result.success(reportService.getStudentDegreeData());
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("生成学生性别分布图");
        return Result.success(reportService.getStudentCountData());
    }
}
