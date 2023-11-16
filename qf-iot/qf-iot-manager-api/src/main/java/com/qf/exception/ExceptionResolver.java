package com.qf.exception;

import com.qf.core.vo.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionResolver {
    @ExceptionHandler(AccessDeniedException.class)
    public R handler(){
        return  new R(false,403,"您没有权限访问目标资源",null);
    }
}
