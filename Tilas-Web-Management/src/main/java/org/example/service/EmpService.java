package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam param);

    //新增员工的方法
    void save(Emp emp);

    //删除员工的方法
    void delete(List<Integer> ids);

    // 根据员工ID查询员工基本信息和工作经历信息
    Emp getInfo(Integer id);

    // 修改员工信息
    void update(Emp emp);

    // 列出所有员工信息
    List<Emp> listAll();

    LoginInfo login(Emp emp);
}
