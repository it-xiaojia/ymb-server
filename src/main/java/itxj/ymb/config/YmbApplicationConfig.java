package itxj.ymb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件中自定义配置
 */
@Component
@ConfigurationProperties(prefix = "ymb.application")
@PropertySource(value = {
		"classpath:application.yml",
		"classpath:application-dev.yml",
		"classpath:application-front.yml",
		"classpath:application-pro.yml"
}, encoding = "UTF-8")
@Data
public class YmbApplicationConfig {
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 项目名
	 */
	private String name;
	/**
	 * 项目版本号
	 */
	private String versionNumber;
	/**
	 * 项目版本代码-[开发版:DEVELOP, 测试版:TEST, 发布版:RELEASE]
	 */
	private String versionTag;
	/**
	 * 允许跨域的地址列表
	 */
	private String[] allowedOrigins;
	/**
	 * 服务IP
	 */
	private String serverIp;
	/**
	 * 环境名称
	 */
	private String envName;
	/**
	 * 无需权限认证的url列表，添加后会直接放通该url下面所有的url
	 */
	private String[] permitUrls;
}
