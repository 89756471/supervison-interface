package zl.com.test.api.controller;

import zl.com.test.api.common.Constants;
import zl.com.test.api.common.ResultBody;
import zl.com.test.api.component.LimitRequest;
import zl.com.test.api.exception.BussinessException;
import zl.com.test.api.service.IAlarmService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlarmController {
    @Autowired
    IAlarmService service;

    @LimitRequest
    @GetMapping(value = {"/alarm/{token}/{corpCode}/{code}", "/alarm/{token}/{corpCode}"})
    public ResultBody alarmList(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode,
                                @PathVariable(value = "code", required = false) String code) throws BussinessException {
        //校验token，增加一个拦截器
        List<Document> list = service.listByOrg(corpCode, code, "", Constants.AlarmQueryType.ALARM);
        this.dealReturnResult(list);
        return ResultBody.success(list);
    }

    @LimitRequest
    @GetMapping("/alarm")
    public ResultBody alarmList1(@RequestParam("token") String token, @RequestParam("corpCode") String corpCode,
                                 @RequestParam(value = "code", required = false) String code, @RequestParam(value = "deviceNames", required = false) String deviceNames) throws BussinessException {
        //校验token，增加一个拦截器
        List<Document> list = service.listByOrg(corpCode, code, deviceNames, Constants.AlarmQueryType.ALARM);
        this.dealReturnResult(list);
        return ResultBody.success(list);
    }
    private void dealReturnResult(List<Document> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        list.forEach(e -> {
            if (e.containsKey("faultMeasureTime") || e.containsKey("alarmMeasureTime")) {
                String time = e.containsKey("faultMeasureTime") ? e.getString("faultMeasureTime") : e.getString("alarmMeasureTime");
                e.put("measureTime", time);
            }
            if (e.containsKey("faultMeasure") || e.containsKey("alarmMeasure")) {
                String measure = e.containsKey("alarmMeasure") ? e.getString("alarmMeasure") : e.getString("faultMeasure");
                e.put("measure", measure);
            }
            if (e.containsKey("faultReason") || e.containsKey("alarmReason")) {
                String reason = e.containsKey("faultReason") ? e.getString("faultReason") : e.getString("alarmReason");
                e.put("reason", reason);
            }
        });

    }

    @LimitRequest
    @GetMapping(value = {"/alarmMonitor/{token}/{corpCode}/{code}", "/alarmMonitor/{token}/{corpCode}"})
    public ResultBody alarmMonitorList(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode,
                                       @PathVariable(value = "code", required = false) String code, @PathVariable(value = "deviceNames", required = false) String deviceNames) {
        //校验token，增加一个拦截器
        List<Document> list = service.listByOrg(corpCode, code, deviceNames, Constants.AlarmQueryType.ALARM_MONITOR);
        this.dealReturnResult(list);
        return ResultBody.success(list);
    }

    @LimitRequest
    @GetMapping("/alarmMonitor")
    public ResultBody alarmMonitorList1(@RequestParam("token") String token, @RequestParam("corpCode") String corpCode,
                                       @RequestParam(value = "code", required = false) String code, @RequestParam(value = "deviceName", required = false) String deviceNames) {
        //校验token，增加一个拦截器
        List<Document> list = service.listByOrg(corpCode, code, deviceNames, Constants.AlarmQueryType.ALARM_MONITOR);
        this.dealReturnResult(list);
        return ResultBody.success(list);
    }
}
