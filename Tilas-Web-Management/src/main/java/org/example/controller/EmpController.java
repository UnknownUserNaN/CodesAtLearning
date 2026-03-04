package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.LogOperation;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page, // 利用RequestParam的defaultValue属性，设置默认值
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end) {
    public Result page(EmpQueryParam param) {
        log.info("分页查询:{}", param);
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }

    @PostMapping
    @LogOperation
    public Result save(Emp emp){
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    @LogOperation
    public Result delete(@RequestParam List<Integer> ids){ // 对于集合类型，Spring不会自动进行参数绑定，必须通过@RequestParam声明
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}") // 实现修改员工时的查询回显
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    @LogOperation
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listAll(){
        log.info("查询所有员工");
        List<Emp> list = empService.listAll();
        return Result.success(list);
    }
}
