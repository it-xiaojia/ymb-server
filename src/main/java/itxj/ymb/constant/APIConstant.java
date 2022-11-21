package itxj.ymb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API常量-用于API业务消息和日志，code含义表示与HTTP状态码一致
 */
@AllArgsConstructor
@Getter
public enum APIConstant {
    SUCCESS(200, "业务请求成功"),
    FORBIDDEN(403, "业务需要重新校验权限"),
    FAIL(404, "业务请求失败"),
    TEST(0, "结尾不调用");

    private final Integer code;
    private String message;

    public APIConstant setMessage(String message) {
        this.message = message;
        return this;
    }
}
