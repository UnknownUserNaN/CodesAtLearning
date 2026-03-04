package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> listByQuery(ClazzQueryParam param) {
        // 通过PageHelper实现分页查询
        PageHelper.startPage(param.getPage(), param.getPageSize()); // 配置分页参数
        List<Clazz> clazzList = clazzMapper.listByQuery(param);
        Page<Clazz> p = (Page<Clazz>) clazzList; // PageHelper会自动拦截SQL查询，并将其替换为之前编写的两条分页查询语句
        return new PageResult<Clazz>(p.getTotal(), p.getResult()); // 调用Page对象内的方法获取总条数和分页信息
    }

    @Override
    public Integer countStuByClazzId(Integer id) {
        return studentMapper.countStuByClazzId(id);
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        // 由于前端传来的数据是不全的，因此这里需要补全创建时间和修改时间这两个属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> listAll() {
        return clazzMapper.listAll();
    }
}
