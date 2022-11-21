package itxj.ymb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页查询条件
 */
@Getter
@Setter
@ToString
public class PageQueryParam implements Serializable {
    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    private Integer currentPage;
    /**
     * 每页记录数
     */
    @NotNull(message = "每页记录数不能为空")
    @Max(value = 20, message = "每页记录数不能大于20")
    private Integer pageSize;
    /**
     * 查询条件
     */
    @Length(max = 255, message = "查询条件字数不能大于255")
    private String queryString;
}
