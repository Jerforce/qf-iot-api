package com.qf.login.filter;

import com.qf.login.entity.Tenant;
import com.qf.login.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TokenFilter extends BasicAuthenticationFilter {


    public TokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        try {
            Tenant tenant = JwtUtil.verifyToken(token);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<String> perms1 = tenant.getPerms();
            if (perms1 != null) {
                for (String perms : perms1) {
                    authorities.add(new SimpleGrantedAuthority(perms));
                }
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tenant, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //放行
        filterChain.doFilter(request, response);

        //清空
        SecurityContextHolder.clearContext();
    }
}
