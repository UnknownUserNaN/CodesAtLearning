package org.example.service;

import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;

import java.util.List;

public interface OperateLogService {
    PageResult<OperateLog> list(Integer page, Integer pageSize);
}
