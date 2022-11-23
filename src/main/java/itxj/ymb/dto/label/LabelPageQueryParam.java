package itxj.ymb.dto.label;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.pojo.Label;
import lombok.*;

import java.io.Serializable;

/**
 * 文章标签查询参数
 */
@Getter
@Setter
@ToString
public class LabelPageQueryParam extends Page<Label> implements Serializable {

}
