package itxj.test.ymb;

import itxj.ymb.YmbServerApplication;
import itxj.ymb.constant.CommonConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试示例代码
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = YmbServerApplication.class)
public class ExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(CommonConstant.LOGGER_NAME);

    @Test
    public void test() {
        LOGGER.debug("测试示例代码");
    }
}
