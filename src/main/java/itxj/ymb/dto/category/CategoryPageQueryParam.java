package itxj.ymb.dto.category;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.pojo.Category;
import lombok.*;

import java.io.Serializable;

/**
 * 文章分类查询参数
 */
@Getter
@Setter
@ToString
public class CategoryPageQueryParam extends Page<Category> implements Serializable {

}
