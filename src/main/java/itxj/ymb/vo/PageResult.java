package itxj.ymb.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageResult<T> implements Serializable {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 当前页结果
     */
    private List<T> rows;
}
