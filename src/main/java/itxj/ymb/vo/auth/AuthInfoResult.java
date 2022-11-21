package itxj.ymb.vo.auth;

import itxj.ymb.pojo.Auth;

import java.io.Serializable;

/**
 * 权限信息结果
 */
public class AuthInfoResult extends Auth implements Serializable {
	public AuthInfoResult(Auth auth) {
		super(auth.getId(), auth.getName(), auth.getUrl(), auth.getParentAuthId(), auth.getIconClass(), auth.getHasChild());
	}
}
