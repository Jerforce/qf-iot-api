package qf;

import com.qf.utils.SMSUtils;
import org.junit.Test;

/**
 * Jerforce
 * @date 2023/11/16 星期四 10:45:21
 */
public class SmsApplicationTests {
    @Test
    public void contextLoads() {
        /**
         * 发送短信
         * @param signName 签名
         * @param templateCode 模板
         * @param phone 手机号
         * @param param 参数
         */
        SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", "15979111501", "9527");
    }
}
