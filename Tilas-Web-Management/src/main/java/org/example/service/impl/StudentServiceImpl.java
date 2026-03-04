package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> listByQuery(StudentQueryParam param) {
        // 通过PageHelper实现分页查询
        PageHelper.startPage(param.getPage(), param.getPageSize()); // 配置分页参数
        List<Student> studentList = studentMapper.listByQuery(param);
        Page<Student> p = (Page<Student>) studentList; // PageHelper会自动拦截SQL查询，并将其替换为之前编写的两条分页查询语句
        return new PageResult<Student>(p.getTotal(), p.getResult()); // 调用Page对象内的方法获取总条
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        studentMapper.addStudent(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void addViolation(Integer id, Integer score) {
        studentMapper.addViolation(id, score);
    }
}
