package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> listByQuery(ClazzQueryParam param);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time)"+
            " values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void addClazz(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz findById(Integer id);

    void update(Clazz clazz);

    @Select("select * from clazz order by update_time desc")
    List<Clazz> listAll();

}
