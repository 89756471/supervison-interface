package zl.com.test.api.service.impl;

import zl.com.test.api.service.ICacheService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CacheSerivceImpl implements ICacheService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Cacheable("getMineCodeByCorp")
    public List<String> getMineCodeByCorp(String corpCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("corpCode").is(corpCode));
        return mongoTemplate.find(query, Document.class, "cmss.mine").stream().map(d -> {
            return d.getString("code");
        }).collect(Collectors.toList());
    }

    @Override
    @Cacheable("getCorp")
    public Map<String, Document> getCorp() {
        List<Document> list = mongoTemplate.find(new Query(), Document.class, "cmss.corp");
        Map<String, Document> map = new HashMap<>();
        list.forEach(e -> {
            String code = e.getString("code");
            map.put(code, e);
        });
        return map;
    }

    @Override
    @Cacheable("getMineByCorp")
    public Map<String, List<Document>> getMineByCorp() {
        Map<String, List<Document>> map = new HashMap<>();
        List<Document> list = mongoTemplate.find(new Query(), Document.class, "cmss.mine");
        list.forEach(e -> {
            String corpCode = e.getString("corpCode");
            if (map.containsKey(corpCode)) {
                map.get(corpCode).add(e);
            } else {
                List<Document> ls = new ArrayList<>();
                ls.add(e);
                map.put(corpCode, ls);
            }

        });
        return map;
    }

    @Override
    @Cacheable("getMine")
    public Map<String, Document> getMine() {
        List<Document> list = mongoTemplate.find(new Query(), Document.class, "cmss.mine");
        Map<String, Document> map = new HashMap<>();
        list.forEach(e -> {
            String code = e.getString("code");
            map.put(code, e);
        });
        return map;
    }
}
