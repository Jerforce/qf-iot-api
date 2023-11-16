package com.qf.user.filter;

import com.qf.user.entity.Menu;
import com.qf.user.entity.User;
import com.qf.user.utils.JwtUtil;
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
            User user  = JwtUtil.verifyToken(token);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<Menu> menuList = user.getMenuList();
            for (Menu menu : menuList) {
                authorities.add(new SimpleGrantedAuthority(menu.getPerms()));
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
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
