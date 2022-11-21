package itxj.ymb.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis管理器-封装一些redis在业务中的操作
 */
@Component
public class RedisManager {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置一个值
     *
     * @param key   key
     * @param value value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置一个值
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间（分钟）
     */
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MINUTES);
    }

    /**
     * 根据key获取一个值
     *
     * @param key key
     * @return 返回一个值
     */
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 如果该key在redis中存在则根据key删除一个值
     *
     * @param key key
     */
    public void delete(String key) {
        if (!isExpire(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断一个key是否过期
     *
     * @param key key
     * @return 过期返回true，反之返回false
     */
    public boolean isExpire(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return value == null || value.trim().equals("");
    }
}
