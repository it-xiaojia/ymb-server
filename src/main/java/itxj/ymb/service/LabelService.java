package itxj.ymb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.label.LabelAddParam;
import itxj.ymb.dto.label.LabelPageQueryParam;
import itxj.ymb.dto.label.LabelUpdateParam;
import itxj.ymb.mapper.LabelMapper;
import itxj.ymb.vo.label.LabelVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 文章标签业务
 */
@Service
@Transactional
public class LabelService {
	@Resource
	private LabelMapper labelMapper;

	public LabelVO queryObject(ObjectOperateParam queryParam) {
		return null;
	}

	public Page<LabelVO> queryPage(LabelPageQueryParam queryParam) {
		return null;
	}

	public void add(LabelAddParam addParam) {

	}

	public void update(LabelUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}
