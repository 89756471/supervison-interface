package zl.com.test.api.service;

import org.bson.Document;

import java.util.List;

public interface IOrgService {
    public List<Document> getMineInfo(String corpCode);

    public List<Document> getOrgInfo(String info);
}
