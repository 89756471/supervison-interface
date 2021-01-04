package zl.com.test.api.service.impl;

import zl.com.test.api.common.Constants;
import zl.com.test.api.service.ICacheService;
import zl.com.test.api.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class IDeviceServiceImpl implements IDeviceService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ICacheService iOrgCacheService;

    @Override
    public List<Document> listByOrg(String corpCode, String codes, String names) {
        log.info("公司{}请求测点数据", corpCode);
        Query query = new Query();
        Constants.EXCULDE_COLUMN.forEach(e -> {
            query.fields().exclude(e);

        });
        List<String> mineCodes = new ArrayList<>();
        if (!StringUtils.isEmpty(codes)) {
            mineCodes.addAll(Arrays.asList(codes.split(",")));
            //校验mineCode是否为当前公司
        } else {
            mineCodes = iOrgCacheService.getMineCodeByCorp(corpCode);
        }
        if (!StringUtils.isEmpty(names)) {
            if (names.indexOf(",") != -1) {
                query.addCriteria(Criteria.where("deviceName").in(Arrays.asList(names.split(","))));
            } else {
                query.addCriteria(Criteria.where("deviceName").is(names));
            }
        }
        query.addCriteria(Criteria.where("mineCode").in(mineCodes));
        return mongoTemplate.find(query, Document.class, "cmss.device");

    }
}
