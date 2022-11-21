package itxj.ymb.service;

import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.mapper.SystemMapper;
import itxj.ymb.vo.system.LastInsertIdResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 系统业务-封装一些通用的数据库操作
 */
@Service
@Transactional
public class SystemService {
	@Resource
	private SystemMapper systemMapper;

	/**
	 * 获取插入成功的数据ID
	 *
	 * @return 返回插入成功的数据ID
	 */
	public LastInsertIdResult getLastInsertId() {
		Integer lastInsertId = systemMapper.getLastInsertId();
		if (lastInsertId == 0) {
			throw new AppRuntimeException("获取插入成功的数据ID失败");
		}
		return LastInsertIdResult.builder().lastInsertId(lastInsertId).build();
	}
}
