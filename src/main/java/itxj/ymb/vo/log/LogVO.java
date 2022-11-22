package itxj.ymb.vo.log;

import itxj.ymb.pojo.Log;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 日志信息结果
 */
@Getter
@Setter
@ToString
public class LogVO implements Serializable {
	private Log log;

	public LogVO(Log log) {
		this.log = log;
	}
}
