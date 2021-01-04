package zl.com.test.api.exception;

import zl.com.test.api.common.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class DefineExceptionHandler {
    @ExceptionHandler(value = BussinessException.class)
    @ResponseBody
    public ResultBody bussinessExceptioResultBodynHandler(HttpServletRequest req, BussinessException ex) {
        log.error("发生业务异常！！原因是{}", ex.getErrorMsg());
        return ResultBody.error(ex.getErrorCode(), ex.errorMsg);
    }
}
