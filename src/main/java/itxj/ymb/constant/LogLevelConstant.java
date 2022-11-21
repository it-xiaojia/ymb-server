package itxj.ymb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据字典常量-日志级别
 */
@AllArgsConstructor
@Getter
public enum LogLevelConstant {
    NORMAL(1, "普通"),
    WARNING(2, "警告"),
    ERROR(3, "错误"),
    TEST(0, "结尾不调用");

    private final Integer code;
    private final String message;
}
