package zl.com.test.api.common;

import zl.com.test.api.exception.CommonEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class ResultBody {
    private int ret;
    private String msg;
    private Object data;

    public static ResultBody success(Object data) {
        ResultBody resultBody = new ResultBody();
        resultBody.setRet(CommonEnum.SUCESS.getResultCode());
        resultBody.setMsg(CommonEnum.SUCESS.getResultMsg());
        resultBody.setData(data);
        return resultBody;
    }

    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setMsg(message);
        rb.setRet(-1);
        rb.setData(null);
        return rb;
    }

    public static ResultBody error(int ret, String msg) {
        ResultBody rb = new ResultBody();
        rb.setMsg(msg);
        rb.setRet(ret);
        rb.setData(null);
        return rb;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
