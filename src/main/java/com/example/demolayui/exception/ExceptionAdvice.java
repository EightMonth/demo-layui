package com.example.demolayui.exception;

import com.example.demolayui.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/7 10:25
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse exceptionHandler(BusinessException e) {
        log.error(e.getMessage(), e);
        ApiResponse ar = new ApiResponse();
        ar.setCode(e.getCode());
        ar.setMsg(e.getMessage());
        return ar;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginFailed(AuthenticationException e) {
        return "login?error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        ApiResponse ar = new ApiResponse();
        ar.setMsg(e.getMessage());
        return ar;
    }
}
