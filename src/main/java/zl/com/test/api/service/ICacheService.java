package zl.com.test.api.service;

import org.bson.Document;

import java.util.List;
import java.util.Map;

public interface ICacheService {
    public List<String> getMineCodeByCorp(String corpCode);


    public Map<String, Document> getCorp();

    public Map<String, List<Document>> getMineByCorp();

    public Map<String,Document> getMine();
}
