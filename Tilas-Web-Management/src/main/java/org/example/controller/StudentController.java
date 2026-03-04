package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.LogOperation;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result listByQuery(StudentQueryParam param){
        log.debug("查询学生信息{}", param);
        return Result.success(studentService.listByQuery(param));
    }

    @DeleteMapping("{ids}")
    @LogOperation
    public Result deleteByIds(@PathVariable Integer[] ids){
        log.debug("删除id为{}的学生", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping
    @LogOperation
    public Result addStudent(@RequestBody Student student){
        log.debug("添加学生{}", student);
        studentService.addStudent(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.debug("查询id为"+id+"的学生");
        return Result.success(studentService.findById(id));
    }

    @PutMapping
    @LogOperation
    public Result update(@RequestBody Student student){
        log.debug("修改学生：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    @LogOperation
    public Result addViolation(@PathVariable Integer id, @PathVariable Integer score){
        log.debug("添加学生id为{}的违规分数{}", id, score);
        studentService.addViolation(id, score);
        return Result.success();
    }
}
