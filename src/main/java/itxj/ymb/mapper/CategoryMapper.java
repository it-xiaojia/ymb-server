package itxj.ymb.mapper;

import itxj.ymb.pojo.Category;

/**
 * 文章类型数据接口
 */
public interface CategoryMapper {
	/**
	 * 通过文章类型ID查询文章类型信息
	 *
	 * @param id 文章类型ID
	 * @return 返回文章类型对象
	 */
	Category findCategoryById(Integer id);
}
