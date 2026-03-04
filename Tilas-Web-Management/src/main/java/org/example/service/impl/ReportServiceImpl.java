package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.JobOption;
import org.example.pojo.StuCountOption;
import org.example.service.EmpService;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        // 组装结果
        List<Object> jobList = list.stream().map(m -> m.get("pos")).toList();
        List<Object> dataList = list.stream().map(m -> m.get("total")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Integer>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }

    @Override
    public StuCountOption getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.getStudentCountData();

        // 组装结果
        List<Object> clazzList = list.stream().map(m -> m.get("name")).toList();
        List<Object> dataList = list.stream().map(m -> m.get("count")).toList();

        return new StuCountOption(clazzList, dataList);
    }
}
