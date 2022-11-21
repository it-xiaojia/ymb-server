package itxj.ymb;

import itxj.ymb.constant.CommonConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("itxj.ymb.mapper")
@SpringBootApplication
public class YmbServerApplication {
    private static final Logger LOGGER = LogManager.getLogger(CommonConstant.LOGGER_NAME);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(YmbServerApplication.class);
        try {
            application.run(args);
            LOGGER.debug("==============================应用启动完成==============================");
        } catch (Exception e) {
            LOGGER.error("系统启动异常", e);
        }
    }

}
