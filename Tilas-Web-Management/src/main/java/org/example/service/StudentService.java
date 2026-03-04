package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

public interface StudentService {
    public PageResult<Student> listByQuery(StudentQueryParam param);

    public void deleteByIds(Integer[] ids);

    public void addStudent(Student student);

    public Student findById(Integer id);

    public void update(Student student);

    public void addViolation(Integer id, Integer score);
}
