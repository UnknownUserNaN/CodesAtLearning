package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.LogOperation;
import org.example.exception.customizedExceptions.DeptNotEmptyException;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    // 定义日志记录对象
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    // @RequestMapping(value="/depts", method={GET})
    @GetMapping
    public Result list() {
        log.debug("查询全部部门的数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    // 辅助函数：根据ID查询部门下的员工数目
    public Integer countEmpByDeptId(Integer id) {
        return deptService.countEmpByDeptId(id);
    }

    @DeleteMapping
    @LogOperation
    public Result delete(Integer id){
        log.debug("尝试删除id为"+id+"的部门");
        if (countEmpByDeptId(id) > 0){
            log.error("该部门下有员工，不能直接删除！");
            throw new DeptNotEmptyException("该部门下有员工，不能直接删除！");
        }
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    @LogOperation
    public Result add(@RequestBody Dept dept){
        log.debug("添加部门："+dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.debug("查询id为"+id+"的部门");
        Dept dept = deptService.findById(id); // 注意，这里根据id查询，由于id是主键，因此只会查询到一条数据包
        return Result.success(dept);
    }

    @PutMapping
    @LogOperation
    public Result update(@RequestBody Dept dept){
        log.debug("修改部门："+dept);
        deptService.update(dept);
        return Result.success();
    }
}
