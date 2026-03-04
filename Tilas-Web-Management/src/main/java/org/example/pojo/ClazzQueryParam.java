package org.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    // 下面这两个参数用于分页查询
    private Integer page = 1;
    private Integer pageSize = 10;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
