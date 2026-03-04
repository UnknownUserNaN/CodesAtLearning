package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.customizedExceptions.ClazzNotEmptyException;
import org.example.exception.customizedExceptions.DeptNotEmptyException;
import org.example.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("服务器发生异常：{}", e.getMessage());
        e.printStackTrace();
        return Result.error("服务器发生异常");
    }

    @ExceptionHandler
    public Result DuplicateKeyException(DuplicateKeyException e){ // 专用来捕获键值重复的异常处理器
        log.error("服务器发生异常：{}", e.getMessage());
        e.printStackTrace();
        String msg = e.getMessage();
        int i = msg.indexOf("Duplicate entry"); // 从错误信息中截取错误键值
        if (i != -1) {
            String[] errKey = msg.substring(i).split(" ");
            return Result.error("键值" + errKey[2] + "】已存在");
        }
        return Result.error("服务器发生异常");
    }

    @ExceptionHandler
    public Result handleClazzNotEmptyException(ClazzNotEmptyException e){
        log.error("服务器发生异常：{}", e.getMessage());
        e.printStackTrace();
        return Result.error("该班级下有学生，不能直接删除！");
    }

    @ExceptionHandler
    public Result handleDeptNotEmptyException(DeptNotEmptyException e){
        log.error("服务器发生异常：{}", e.getMessage());
        e.printStackTrace();
        return Result.error("该部门下有员工，不能直接删除！");
    }
}
