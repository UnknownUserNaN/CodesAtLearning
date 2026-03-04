package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> listByQuery(ClazzQueryParam param);

    void deleteById(Integer id);

    void addClazz(Clazz clazz);

    Clazz findById(Integer id);

    void update(Clazz clazz);

    List<Clazz> listAll();

    Integer countStuByClazzId(Integer id);
}
