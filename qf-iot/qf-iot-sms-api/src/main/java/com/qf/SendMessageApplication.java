package com.qf;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Jerforce
 * @date 2023/11/16 星期四 19:15:43
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.qf.*.mapper"})
public class SendMessageApplication {
    private static final Log LOG = LogFactory.getLog(SendMessageApplication.class);
}
