package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> { // 这里使用Java的泛型，让展示的结果更加多样
    private Long total; // 总记录数
    private List<T> rows; // 当前页数据
}
