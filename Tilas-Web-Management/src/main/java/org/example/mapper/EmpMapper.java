package org.example.mapper;

import org.apache.ibatis.annotations.*;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
//    @Select("select e.*, d.name dept_name from emp e left join dept d on e.dept_id = d.id "+
//            "order by e.update_time desc")
//    public List<Emp> list(); // 改用PageHelper实现分页查询

    // 这里的查询语句定义改用xml文件映射实现
//    @Select("select e.*, d.name dept_name from emp e left join dept d on e.dept_id = d.id "+
//            "where e.name like '%#{name}#' and e.gender = #{gender} and e.entry_date between #{start} and #{end} "+
//            "order by e.update_time desc")
    public List<Emp> listWithCondition(EmpQueryParam param); // 改用PageHelper实现分页查询

    // 新增员工基本信息的Mapper
    @Options(useGeneratedKeys = true, keyProperty = "id") // 这里是Mybatis提供的方法，用于完成主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("pos")
    List<Map<String, Object>> countEmpGenderData();

    @Select("select id, username, name, password, entry_date, gender, image, job, salary, dept_id, create_time, update_time from emp")
    List<Emp> listAll();

    @Select("select count(*) from emp where dept_id = #{id}")
    Integer countEmpByDeptId(Integer id);

    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
