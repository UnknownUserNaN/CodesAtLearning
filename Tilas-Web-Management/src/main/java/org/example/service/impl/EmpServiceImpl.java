package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam param) {
        // 通过PageHelper实现分页查询
        PageHelper.startPage(param.getPage(), param.getPageSize()); // 配置分页参数
        List<Emp> empList = empMapper.listWithCondition(param); // PageHelper会自动拦截SQL查询，并将其替换为之前编写的两条分页查询语句
        // 需要注意的是，PageHelper只会拦截并修改其配置分页参数后的第一次SQL查询
        Page<Emp> p = (Page<Emp>) empList; // 将查询结果List强制转换为Page对象（Page对象实际上是List的子类）
        return new PageResult<Emp>(p.getTotal(), p.getResult()); // 调用Page对象内的方法获取总条数和分页信息
    }

    @Transactional(rollbackFor = {Exception.class}) // 无论出现什么异常，都回滚
    @Override
    public void save(Emp emp){
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp); // 初始时emp中的id属性为空，但是经过Mapper中的Options注解设置后，此行运行完毕后，emp中的id属性会自动置位

            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) { //若员工工作经历信息非空，再保存工作信息
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        }finally{
            //无论如何，都记录操作日志（所以使用try-catch块）
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids){
        // 批量删除员工的基本信息
        empMapper.deleteByIds(ids);

        // 批量删除员工工作信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 修改基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 删除员工原来的工作信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        // 添加员工新的工作信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> listAll(){
        return empMapper.listAll();
    }

    @Override
    public LoginInfo login(Emp emp){
        // 调用Mapper接口，根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        // 判断是否存在这个员工，如果存在，则组装登录成功信息，否则返回空
        if (e != null) {
            log.info("员工登录成功：{}", e);

            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("name", e.getName());
            String jwt = JwtUtils.generateJwt(claims); // JWT令牌字符串

            return new LoginInfo(e.getId(), e.getUsername(), e.getPassword(), jwt);
        }
        return null;

    }
}
