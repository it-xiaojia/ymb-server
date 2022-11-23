package itxj.ymb.dto.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.pojo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户分页查询参数
 */
@Getter
@Setter
@ToString
public class UserPageQueryParam extends Page<User> implements Serializable {

}
