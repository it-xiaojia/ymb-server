package itxj.ymb.vo.auth;

import itxj.ymb.pojo.Auth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 权限信息结果
 */
@Getter
@Setter
@ToString
public class AuthVO extends Auth implements Serializable {
	private Auth auth;

	public AuthVO(Auth auth) {
		this.auth = auth;
	}
}
