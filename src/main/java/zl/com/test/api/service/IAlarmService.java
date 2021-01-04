package zl.com.test.api.service;

import org.bson.Document;

import java.util.List;

public interface IAlarmService {
    public List<Document> listByOrg(String corpCode, String codes, String deviceName, int type);

}
