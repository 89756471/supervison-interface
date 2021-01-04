package zl.com.test.api.controller;

import zl.com.test.api.common.ResultBody;
import zl.com.test.api.component.LimitRequest;
import zl.com.test.api.service.IPlanOperService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanOperController {
    @Autowired
    IPlanOperService service;

    @LimitRequest
    @GetMapping(value = {"/planInfo/{token}/{corpCode}/{code}", "/planInfo/{token}/{corpCode}"})
    public ResultBody realTime(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode,
                               @PathVariable(value = "code", required = false) String code) {
        List<Document> list = service.listByOrg(corpCode, code);
        return ResultBody.success(list);
    }

}
