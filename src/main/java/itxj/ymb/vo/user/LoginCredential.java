package itxj.ymb.vo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 登录成功后返回的凭证
 */
@AllArgsConstructor
@Getter
@ToString
public class LoginCredential implements Serializable {
	/**
	 * 没有token下的部分敏感接口操作凭证
	 */
	private final String noTokenCredential;
	/**
	 * 更新accessToken需要的token
	 */
	private final String refreshToken;
	/**
	 * 业务请求token
	 */
	private final String accessToken;
}
