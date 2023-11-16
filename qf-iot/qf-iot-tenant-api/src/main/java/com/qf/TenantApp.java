package com.qf;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Jerforce
 * @date 2023/11/16 星期四 19:21:27
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.qf.*.mapper"})
public class TenantApp {
    @Bean
    public MybatisPlusInterceptor getMybatisPlusInterceptor() {
        //1.创建MyBatisPlus拦截器对象
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
       // 2.创建分页拦截器
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
      //  3.返回MyBatisPlus拦截器对象
        return mybatisPlusInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(TenantApp.class, args);
    }
}
