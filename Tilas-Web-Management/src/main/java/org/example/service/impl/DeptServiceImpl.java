package org.example.service.impl;

import org.example.mapper.DeptMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public Integer countEmpByDeptId(Integer id) {
        return empMapper.countEmpByDeptId(id);
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        // 由于前端传来的数据是不全的，因此这里需要补全创建时间和修改时间这两个属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        // 新增部门
        deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        // 由于前端传来的数据是不全的，因此这里需要补全修改时间这两个属性
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }
}
