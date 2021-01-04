package zl.com.test.api.service;

import org.bson.Document;

import java.util.List;

public interface IPlanOperService {
    public List<Document> listByOrg(String corpCode, String codes);


}
