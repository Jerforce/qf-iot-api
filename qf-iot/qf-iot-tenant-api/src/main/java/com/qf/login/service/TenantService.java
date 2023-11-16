package com.qf.login.service;

import com.qf.core.vo.Result;
import com.qf.login.entity.Tenant;

public interface TenantService {
    Result login(Tenant tenant);
    Result register(Tenant tenant);


}
