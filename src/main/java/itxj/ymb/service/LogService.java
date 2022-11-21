package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.log.AddParam;
import itxj.ymb.dto.log.UpdateParam;
import itxj.ymb.mapper.LogMapper;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.log.LogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志业务
 */
@Service
@Transactional
public class LogService {
	@Resource
	private LogMapper logMapper;

	public LogVO queryObject(ObjectQueryParam queryParam) {
		return null;
	}

	public List<PageResult> queryList(itxj.ymb.dto.log.ListQueryParam queryParam) {
		return null;
	}

	public void add(AddParam addParam) {

	}

	public void update(UpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}
