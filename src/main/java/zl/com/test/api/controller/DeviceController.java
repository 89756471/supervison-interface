package zl.com.test.api.controller;

import zl.com.test.api.common.ResultBody;
import zl.com.test.api.component.LimitRequest;
import zl.com.test.api.service.IDeviceService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {
    @Autowired
    IDeviceService service;

    @LimitRequest
    @GetMapping(value = {"/device/{token}/{corpCode}/{code}", "/device/{token}/{corpCode}"})
    public ResultBody realTime(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode,
                               @PathVariable(value = "code", required = false) String code) {
        List<Document> list = service.listByOrg(corpCode, code, "");
        return ResultBody.success(list);
    }

    @LimitRequest
    @GetMapping(value = "/device")
    public ResultBody realTime1(@RequestParam("token") String token, @RequestParam("corpCode") String corpCode,
                                @RequestParam(value = "code", required = false) String code,@RequestParam(value = "deviceName", required = false) String deviceName) {
        List<Document> list = service.listByOrg(corpCode, code, deviceName);
        return ResultBody.success(list);
    }

}
