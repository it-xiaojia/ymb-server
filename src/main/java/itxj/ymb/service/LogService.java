package itxj.ymb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.log.LogAddParam;
import itxj.ymb.dto.log.LogPageQueryParam;
import itxj.ymb.mapper.LogMapper;
import itxj.ymb.vo.log.LogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 日志业务
 */
@Service
@Transactional
public class LogService {
	@Resource
	private LogMapper logMapper;

	public LogVO queryObject(ObjectOperateParam queryParam) {
		return null;
	}

	public Page<LogVO> queryPage(LogPageQueryParam queryParam) {
		return null;
	}

	public void add(LogAddParam addParam) {

	}

	public void clear(DeleteParam deleteParam) {

	}
}
