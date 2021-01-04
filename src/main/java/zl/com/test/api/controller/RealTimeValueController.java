package zl.com.test.api.controller;

import zl.com.test.api.common.ResultBody;
import zl.com.test.api.component.LimitRequest;
import zl.com.test.api.service.IRealTimeValueService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RealTimeValueController {
    @Autowired
    IRealTimeValueService service;

    @LimitRequest
    @GetMapping(value = {"/realTime/{token}/{corpCode}/{code}", "/realTime/{token}/{corpCode}"})
    public ResultBody realTime(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode,
                               @PathVariable(value = "code", required = false) String code) {
        //校验token，增加一个拦截器
        List<Document> list = service.listByOrg(corpCode, code, "");
        return ResultBody.success(list);
    }

    @LimitRequest
    @GetMapping(value = "/realTime")
    public ResultBody realTime(@RequestParam("token") String token, @RequestParam("corpCode") String corpCode,
                               @RequestParam(value = "code", required = false) String code, @RequestParam(value = "deviceName", required = false) String deviceName) {
        //校验token，增加一个拦截器
        List<Document> list = service.listByOrg(corpCode, code, deviceName);
        return ResultBody.success(list);
    }

    @GetMapping(value = "/test1")
    public String test1() {
        //校验token，增加一个拦截器
        return "fffffffffffff";
    }

    @PostMapping(value = "/test")
    public ResultBody test(@RequestBody String ss) {
        //校验token，增加一个拦截器
        System.out.println(ss);
        return new ResultBody();
    }

}
