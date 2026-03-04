package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.LogOperation;
import org.example.exception.customizedExceptions.ClazzNotEmptyException;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    // 班级列表数据的条件分页查询
    @GetMapping
    public Result listByQuery(ClazzQueryParam param) {
        log.debug("按条件查询的班级数据{}", param);
        return Result.success(clazzService.listByQuery(param));
    }

    // 辅助函数：根据ID查询班级下的学生数目
    private int countStuByClazzId(Integer id) {
        return clazzService.countStuByClazzId(id);
    }

    // 根据ID删除班级
    @DeleteMapping("/{id}")
    @LogOperation
    public Result deleteById(@PathVariable Integer id){
        log.debug("尝试删除id为"+id+"的班级");
        if (countStuByClazzId(id) > 0) {
            log.error("该班级下有学生，不能直接删除！");
            throw new ClazzNotEmptyException("该班级下有学生，不能直接删除！");
        }
        clazzService.deleteById(id);
        return Result.success();
    }

    // 新增班级
    @PostMapping
    @LogOperation
    public Result addClazz(@RequestBody Clazz clazz){
        log.debug("添加班级："+clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    // 根据ID查询班级
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.debug("查询id为"+id+"的班级");
        return Result.success(clazzService.findById(id));
    }

    // 修改班级
    @PutMapping
    @LogOperation
    public Result update(@RequestBody Clazz clazz){
        log.debug("修改班级："+clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    // 查询所有班级
    @GetMapping("/list")
    public Result listAll(){
        log.debug("查询所有班级");
        return Result.success(clazzService.listAll());
    }



}
