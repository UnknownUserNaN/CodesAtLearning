package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.StuCountOption;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> listByQuery(StudentQueryParam param);

    void deleteByIds(Integer[] ids);

    void addStudent(Student student);

    @Select("select * from student where id = #{id}")
    Student findById(Integer id);

    void update(Student student);

    public void addViolation(Integer id, Integer score);

    // 以下两个是用作数据统计的接口
    @MapKey("name")
    List<Map<String, Integer>> getStudentDegreeData();

    @MapKey("name")
    List<Map<String, Object>> getStudentCountData();

    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countStuByClazzId(Integer id);
}
