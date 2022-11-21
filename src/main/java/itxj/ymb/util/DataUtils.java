package itxj.ymb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import itxj.ymb.constant.CommonConstant;
import itxj.ymb.mapper.UserMapper;
import itxj.ymb.pojo.User;
import itxj.ymb.vo.TokenVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 封装一些常用到的数据工具类
 */
public class DataUtils {
    private static final Logger LOGGER = LogManager.getLogger(CommonConstant.LOGGER_NAME);
    /**
     * token中携带数据的key值
     */
    public static final String TOKEN_KEY = "USER_ACCOUNT";
    /**
     * token加密私钥
     */
    private static final String TOKEN_PRIVATE_KEY = "=-0WM(&xj%^`,,.";
    /**
     * 设置refreshToken的有效期：60分钟
     */
    private static final long REFRESH_TOKEN_EXPIRE = 60;
    /**
     * 设置accessToken的有效期：15分钟
     */
    private static final long ACCESS_TOKEN_EXPIRE = 15;

    private DataUtils() {
    }

    /**
     * 将当前日期转换成yyyy-MM-dd HH:mm:ss的字符串格式
     *
     * @return 返回转换好的字符串
     */
    public static String convertCurrentTimeToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = Calendar.getInstance().getTime();
        return dateFormat.format(time);
    }

    /**
     * 将字符串加密成md5
     *
     * @param str 字符串
     * @return 返回加密完成的md5
     */
    public static String stringToMd5(String str) {
        return DigestUtils.md5Hex(str + "*&@itxj./,0*X`");
    }

    /**
     * 根据用户账号生成refreshToken
     *
     * @param tokenPrivateKey token私钥，由客户端传入后的密码进行md5加密后生成
     * @param account         用户账号
     * @return 返回生成好的refreshToken，如果过期，则需要用户重新登录来获取
     */
    private static String generateRefreshToken(String account, String tokenPrivateKey) {
        return JWT.create()
                // 将用户账号存入token中
                .withClaim(TOKEN_KEY, account)
                // 签发时间
                .withIssuedAt(new Date())
                // 将UUID和系统当前时间戳作为token的key
                .withKeyId(UUID.randomUUID() + CommonConstant.PUBLIC_SPLIT + System.currentTimeMillis())
                // 过期时间-1小时
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE * 60000))
                // 指定签名算法
                .sign(Algorithm.HMAC256(tokenPrivateKey));
    }

    /**
     * 根据refreshToken生成accessToken
     *
     * @param refreshToken refreshToken
     * @return 返回生成好的accessToken，如果过期，需要拿未过期的refreshToken来生成
     */
    private static String generateAccessToken(String refreshToken) {
        // 解析refreshToken中携带的用户账号信息
        String account = JWT.decode(refreshToken).getClaims().get(TOKEN_KEY).toString();
        LOGGER.debug("解析出来的账号信息：" + account);
        return JWT.create()
                // 将用户账号存入token中
                .withClaim(TOKEN_KEY, account.replace("\"", ""))
                // 签发时间
                .withIssuedAt(new Date())
                // 将UUID和系统当前时间戳作为token的key
                .withKeyId(UUID.randomUUID() + CommonConstant.PUBLIC_SPLIT + System.currentTimeMillis())
                // 过期时间-15分钟
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE * 60000))
                // 指定签名算法
                .sign(Algorithm.HMAC256(TOKEN_PRIVATE_KEY));
    }

    /**
     * 生成带有双token的TokenVO对象并在redis中存储并设置各自的过期时间（分钟）
     *
     * @param redisManager RedisManager对象
     * @param account      经过MD5加密后的账号，存储在refreshToken的redis中，有效期为1小时
     * @param password     经过MD5加密后的密码，存储在accessToken的redis中，有效期为15分钟
     * @return 返回TokenVO对象
     */
    public static TokenVO generateTokenVO(RedisManager redisManager, String account, String password, String userInfo) {
        String refreshToken = generateRefreshToken(account, password);
        String accessToken = generateAccessToken(refreshToken);
        LOGGER.debug("===生成的token信息：");
        LOGGER.debug("===refreshToken：" + refreshToken);
        LOGGER.debug("===accessToken：" + accessToken);
        redisManager.set(refreshToken, userInfo, REFRESH_TOKEN_EXPIRE);
        LOGGER.debug("===成功往redis中存入refreshToken，值为用户id+@+username，过期时间为60分钟");
        redisManager.set(accessToken, userInfo, ACCESS_TOKEN_EXPIRE);
        LOGGER.debug("===成功往redis中存入accessToken，值为用户id+@+username，过期时间为15分钟");
        return new TokenVO(refreshToken, accessToken);
    }

    /**
     * 删除redis中的token信息
     *
     * @param redisManager RedisManager
     * @param refreshToken refreshToken
     * @param accessToken  accessToken
     */
    public static void deleteRedisTokenInfo(RedisManager redisManager, String refreshToken, String accessToken) {
        if (refreshToken != null && !redisManager.isExpire(refreshToken)) {
            redisManager.delete(refreshToken);
            LOGGER.debug("===成功删除refreshToken在redis中的信息：");
        }
        if (accessToken != null && !redisManager.isExpire(accessToken)) {
            redisManager.delete(accessToken);
            LOGGER.debug("===成功删除accessToken在redis中的信息：");
        }
    }

    /**
     * 根据redis中存储的用户账号来校验用户传入的token<br/>
     * refreshToken的校验需要自定义私钥，accessToken的校验需要程序内部定义的私钥
     *
     * @param tokenPrivateKey token自定义私钥
     * @param token           用户传入的token
     * @param account         用户账号
     * @return 校验成功返回true，失败返回false
     */
    public static boolean verifyToken(String tokenPrivateKey, String token, String account) {
        try {
            // 默认使用类中定义的私钥
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_PRIVATE_KEY);
            // 如果传入的自定义私钥不为空，则用自定义私钥
            if (tokenPrivateKey != null) {
                algorithm = Algorithm.HMAC256(tokenPrivateKey);
            }
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(TOKEN_KEY, account)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户对象生成指定的用户信息
     *
     * @param user 用户对象
     * @return 返回用户ID+@+username的用户信息格式
     */
    public static String generateUserInfo(User user) {
        return user.getId() + CommonConstant.PUBLIC_SPLIT + user.getUsername();
    }

    /**
     * 通过token查询用户信息
     *
     * @param userMapper   UserMapper
     * @param redisManager RedisManager
     * @param token        含有用户ID的token
     * @return 返回用户对象
     */
    public static User queryUserInfoByToken(UserMapper userMapper, RedisManager redisManager, String token) {
        Integer userId = Integer.parseInt(redisManager.get(token).split(CommonConstant.PUBLIC_SPLIT)[0]);
        return userMapper.selectById(userId);
    }

    /**
     * 判断一个字符串是否不为空
     *
     * @param str 传入的字符串
     * @return 不为空返回true，为空返回false
     */
    public static boolean isStringNotNull(String str) {
        return str != null && !(str.trim().equals(""));
    }
}
