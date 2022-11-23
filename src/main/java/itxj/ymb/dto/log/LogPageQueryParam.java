package itxj.ymb.dto.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.pojo.Log;
import lombok.*;

import java.io.Serializable;

/**
 * 日志分页查询参数
 */
@Getter
@Setter
@ToString
public class LogPageQueryParam extends Page<Log> implements Serializable {

}
