package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.label.AddParam;
import itxj.ymb.dto.label.UpdateParam;
import itxj.ymb.mapper.LabelMapper;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.label.LabelVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章标签业务
 */
@Service
@Transactional
public class LabelService {
	@Resource
	private LabelMapper labelMapper;

	public LabelVO queryObject(ObjectQueryParam queryParam) {
		return null;
	}

	public List<PageResult> queryList(itxj.ymb.dto.label.ListQueryParam queryParam) {
		return null;
	}

	public void add(AddParam addParam) {

	}

	public void update(UpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}
