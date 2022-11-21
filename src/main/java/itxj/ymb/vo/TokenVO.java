package itxj.ymb.vo;

import itxj.ymb.constant.CommonConstant;
import lombok.Getter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * token
 */
@Getter
@ToString
public class TokenVO implements Serializable {
    /**
     * 过期的refreshToken（refreshToken默认值）
     */
    public static final String EXPIRE_REFRESH_TOKEN = "eyJraWQiOiJkM2FkNmZkZi0zNTM5LTQ5ZTAtYjMzZS1mMDgwNzYyMzM5NTVAMTY1ODk3NzY2MjY5NyIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE2NTg5ODEyNjIsImlhdCI6MTY1ODk3NzY2MiwiVVNFUl9BQ0NPVU5UIjoiNTUxNTQ2MzE4NTcyMDM2NWNhMWQxNWJjY2FmOTVlY2UifQ.quRF9AQ55oGBYFDUBkQ6dy_wfVr0vtZMJk7VqfmfP5M";
    /**
     * 过期的accessToken（accessToken默认值）
     */
    public static final String EXPIRE_ACCESS_TOKEN = "eyJraWQiOiI2NGQ5NGJhNy03MDk1LTQ1OTctODlkOC1hYTExODAzZTNmMGZAMTY1ODk3NzY2MjcwMCIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE2NTg5Nzg1NjIsImlhdCI6MTY1ODk3NzY2MiwiVVNFUl9BQ0NPVU5UIjoiNTUxNTQ2MzE4NTcyMDM2NWNhMWQxNWJjY2FmOTVlY2UifQ.b5zqESe-suVx1k8dky7AhZ7CAOlIdT5aJmK2cXj6EX8";
    /**
     * 更新accessToken需要的token
     */
    private final String refreshToken;
    /**
     * 业务请求token
     */
    private final String accessToken;

    /**
     * 通过Http请求构造TokenVO对象
     *
     * @param request Http请求
     */
    public TokenVO(HttpServletRequest request) {
        // 获取header中的authorization信息
        // ApiPost环境
//        String authorization = request.getHeader("Authorization");
        // 非ApiPost环境
        String authorization = request.getHeader("Authentication");
        if (authorization != null) {
            // 前端传入的校验双token应该由@符号分隔开，后端根据@符号分割出来refreshToken（@符左边的字符串）和accessToken（@符右边的字符串）
            String[] authInfos = authorization.split(CommonConstant.PUBLIC_SPLIT);
            if (authInfos.length == 2) {
                // 前端使用bearer auth认证传token时
                // 传过来的token会自动在最开头添加一个Bearer来说明验证类型，refreshToken在@符左边，拿过来会带有Bearer字符串
                // 需要去掉这个字符串来保证token的正确性
                refreshToken = authInfos[0].split(" ")[1];
                accessToken = authInfos[1];
            } else {
                refreshToken = EXPIRE_REFRESH_TOKEN;
                accessToken = EXPIRE_ACCESS_TOKEN;
            }
        } else {
            refreshToken = EXPIRE_REFRESH_TOKEN;
            accessToken = EXPIRE_ACCESS_TOKEN;
        }
    }

    /**
     * 直接使用字符串token来构造TokenVO对象
     *
     * @param refreshToken refreshToken
     * @param accessToken  accessToken
     */
    public TokenVO(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
