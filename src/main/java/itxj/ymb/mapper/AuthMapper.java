package itxj.ymb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itxj.ymb.dto.auth.AuthListQueryParam;
import itxj.ymb.pojo.Auth;

import java.util.List;

/**
 * 权限数据接口
 */
public interface AuthMapper extends BaseMapper<Auth> {
	/**
	 * 自定义列表查询
	 *
	 * @param queryParam 列表查询参数
	 * @return 返回权限列表
	 */
	List<Auth> customSelectList(AuthListQueryParam queryParam);
}
