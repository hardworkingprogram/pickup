package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private boolean success; // 响应状态（成功/失败）
    private String message; // 提示信息
    private T data; // 响应数据（泛型类型）

    // 成功响应方法
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, message, data);
    }

    // 失败响应方法
    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }
}