package org.example.service;

import org.example.pojo.JobOption;
import org.example.pojo.StuCountOption;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Integer>> getStudentDegreeData();

    StuCountOption getStudentCountData();
}
