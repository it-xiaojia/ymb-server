package itxj.ymb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 数据字典常量-账号状态
 */
@AllArgsConstructor
@Getter
public enum AccountStatusConstant implements Serializable {
    NO_LOGIN(1, "未登录"),
    IS_LOGIN(2, "已登录"),
    BAN(3, "账号封禁"),
    TEST(0, "结尾不调用");

    private final Integer code;
    private final String message;
}
