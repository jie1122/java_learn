package com.example.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /*全局sql异常*/
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        String errMessage = exception.getMessage();
        log.info("sql异常    --->   " + errMessage);
        if (errMessage.contains("Duplicate entry")) {
            String[] strings = errMessage.split(" ");
            return R.error(strings[2] + "已存在");
        }
        return R.error("操作失败");
    }

    /*全局业务异常*/
    @ExceptionHandler(MyException.class)
    public R<String> exceptionHandler(MyException myException) {
        String message = myException.getMessage();
        return R.error(message);
    }
}
