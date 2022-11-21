package itxj.ymb.mapper;

import itxj.ymb.pojo.Label;

import java.util.List;

/**
 * 文章标签数据接口
 */
public interface LabelMapper {
	/**
	 * 查询文章标签列表，如果文章ID不为空，则查询指定文章拥有的标签列表
	 *
	 * @param articleId 文章ID
	 * @return 返回被查询文章的标签列表
	 */
	List<Label> findLabelList(Integer articleId);
}
