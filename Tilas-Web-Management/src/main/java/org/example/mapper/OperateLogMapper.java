package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    // 插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    // 查询返回日志数据
    @Select("select o.*, e.name as operate_emp_name from operate_log as o left join emp as e on o.operate_emp_id = e.id")
    public List<OperateLog> list();

}

