package itxj.ymb.config;

import itxj.ymb.constant.CommonConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;

/**
 * 应用程序配置
 */
@Configuration(proxyBeanMethods = false)
public class ApplicationConfig implements WebMvcConfigurer {
	private static final Logger LOGGER = LogManager.getLogger(CommonConstant.LOGGER_NAME);
	@Resource
	private YmbApplicationConfig ymbApplicationConfig;
	@Value("${server.port}")
	private String port;
	@Value("${server.servlet.context-path}")
	private String contextPath;

	/**
	 * 全局配置跨源请求
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		LOGGER.debug("=====当前环境：" + ymbApplicationConfig.getEnvName() + "环境");
		LOGGER.debug("=====服务访问地址：" + ymbApplicationConfig.getServerIp() + ":" + port + contextPath);
		String[] allowedOrigins = ymbApplicationConfig.getAllowedOrigins();
		LOGGER.debug("=====允许跨域地址列表：" + Arrays.toString(allowedOrigins));
		registry.addMapping("/**")
				// 配置允许跨域的前端请求url
				.allowedOrigins(allowedOrigins);
	}

	/**
	 * 配置Validator的模式
	 */
	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
				.configure()
				// true-快速失败返回模式    false-普通模式
				.addProperty("hibernate.validator.fail_fast", "true")
				.buildValidatorFactory();
		return validatorFactory.getValidator();
	}
}
