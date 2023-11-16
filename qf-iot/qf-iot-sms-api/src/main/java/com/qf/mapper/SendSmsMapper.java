package com.qf.mapper;

import com.qf.pojo.TenantsPhone;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Jerforce
 * @date 2023/11/16 星期四 11:43:20
 */
@Mapper
@Repository
public interface SendSmsMapper {
    /*根据手机号码查询所使用的短信模板*/
    TenantsPhone selectPhone(@Param("phone") String phone);

    /*保存生成的验证码到数据库*/
    void insertPhoneCode(@Param("phoneCode") Integer phoneCode,String format);
}
