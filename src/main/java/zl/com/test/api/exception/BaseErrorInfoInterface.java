package zl.com.test.api.exception;

/**
 * 自定义异常处理接口
 */
public interface BaseErrorInfoInterface {
    int getResultCode();

    String getResultMsg();
}
