package zl.com.test.api.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation( value = "查询组织机构", notes = "查询组织机构信息", httpMethod = "GET" )
//    @ApiImplicitParam(name = "token", value = "公司token", required = true, dataType = "String", paramType = "path")
    public ResultBody realTime(@ApiParam(name = "token", value = "下发公司的token", required = true)@PathVariable("token") String token, @ApiParam(name = "corpCode", value = "公司编码", required = true)@PathVariable("corpCode") String corpCode) {
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
