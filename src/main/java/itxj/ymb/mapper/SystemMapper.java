package itxj.ymb.mapper;

/**
 * 系统数据接口
 */
public interface SystemMapper {
	/**
	 * 获取插入成功的数据ID
	 *
	 * @return 返回插入成功的数据ID
	 */
	Integer getLastInsertId();
}
