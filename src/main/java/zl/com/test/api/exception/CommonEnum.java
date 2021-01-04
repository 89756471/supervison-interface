package zl.com.test.api.exception;

/**
 * 异常错误编码
 */
public enum CommonEnum implements BaseErrorInfoInterface {
    SUCESS(200, "成功"),
    BODY_NOT_MATCH(400, "客户端参数错误"),
    SIGNATURE_NOT_MATCH(401, "接口请求次数超限(请求频率不能低于30秒每次)"),
    NOT_FOUND(404, "接口服务不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试");
    private int resultCode;
    private String resultMsg;

    CommonEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    @Override
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
