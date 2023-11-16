package com.qf.core.dto;

import com.qf.login.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantDTO extends Tenant {
    private Tenant tenant;
    private String token;
}
