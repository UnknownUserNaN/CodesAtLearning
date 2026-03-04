package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> list(Integer page, Integer pageSize) {
        // 通过PageHelper实现分页查询
        PageHelper.startPage(page, pageSize); // 配置分页参数
        List<OperateLog> logList = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) logList; // PageHelper会自动拦截SQL查询，并将其替换为之前编写的两条分页查询语句
        return new PageResult<OperateLog>(p.getTotal(), p.getResult()); // 调用Page对象内的方法获取总条
    }
}
