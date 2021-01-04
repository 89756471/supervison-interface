package zl.com.test.api.service.impl;

import zl.com.test.api.service.ICacheService;
import zl.com.test.api.service.IOrgService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IOrgServiceImpl implements IOrgService {
    @Autowired
    private ICacheService cacheService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Document> getMineInfo(String corpCode) {
        log.info("公司{}请求组织信息数据", corpCode);
        Map<String, List<Document>> map = cacheService.getMineByCorp();
        if (map.containsKey(corpCode)) {
            return map.get(corpCode);
        }
        return null;
    }

    @Override
    public List<Document> getOrgInfo(String info) {
        Query query = new Query();
      return  mongoTemplate.find(query,Document.class,"test.testUserTime");
    }
}
