package zl.com.test.api.controller;

import zl.com.test.api.common.ResultBody;
import zl.com.test.api.component.LimitRequest;
import zl.com.test.api.service.IOrgService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrgInfoController {

    @Autowired
    IOrgService service;


    @LimitRequest
    @GetMapping(value = {"org/{token}/{corpCode}"})
    public ResultBody realTime(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode) {
        //校验token，增加一个拦截器
        List<Document> list = service.getMineInfo(corpCode);
        return ResultBody.success(list);
    }

    @GetMapping(value = {"orgTest/{token}/{corpCode}"})
    public ResultBody orgTest(@PathVariable("token") String token, @PathVariable("corpCode") String corpCode) {
        //校验token，增加一个拦截器
        List<Document> list = service.getOrgInfo(corpCode);
        return ResultBody.success(list);
    }
}
