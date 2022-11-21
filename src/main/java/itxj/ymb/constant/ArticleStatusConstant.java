package itxj.ymb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据字典常量-文章状态
 */
@AllArgsConstructor
@Getter
public enum ArticleStatusConstant {
    NO_PUBLISH(1, "未发布"),
    PUBLISHED(2, "已发布"),
    TEST(0, "结尾不调用");

    private final Integer code;
    private final String message;
}
