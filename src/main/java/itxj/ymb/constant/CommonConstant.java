package itxj.ymb.constant;

/**
 * 业务代码中常用的一些常量
 */
public final class CommonConstant {
    /**
     * 自定义的日志名称
     */
    public static final String LOGGER_NAME = "ymb-debug";
    /**
     * 公用分隔符
     */
    public static final String PUBLIC_SPLIT = "@";
    /**
     * redis存储无token访问凭证的key，应该与用户ID进行拼接
     */
    public static final String USER_CREDENTIAL_REDIS_KEY = "noTokenAccess";
}
