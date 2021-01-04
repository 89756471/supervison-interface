package zl.com.test.api.task;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zl.com.test.api.common.Constants;
import zl.com.test.api.service.IAlarmService;
import zl.com.test.api.service.ICacheService;
import zl.com.test.api.util.RestTemplateUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 定时推送数据接口
 */
@Component
public class PushDataToBigScreen {
    @Autowired
    IAlarmService alarmService;
    @Value("${pushdata.url}")
    private String url;
    @Value("${pushdata.token}")
    private String tokenUrl;
    @Autowired
    private ICacheService iCacheService;
    @Value("${isOpenTask}")
    private boolean isOpenTask;

    @PostConstruct
    public void pushData() {
        if (isOpenTask) {
            ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory(DefaultThreadFactory.MIDDLE_PRIOPITY));
            executorService.scheduleWithFixedDelay(this::statisticByMineCode, 0, 20, TimeUnit.SECONDS);
        }
    }

    private String getToken() {
        try {
            String resutl = RestTemplateUtils.doGet(tokenUrl);
            return resutl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void statisticByMineCode() {
        String token = this.getToken();
        List<Document> list = alarmService.listByOrg("10000000","","", Constants.AlarmQueryType.ALARM);
        List dataList = new ArrayList();
        if (null != list && !list.isEmpty()) {
            Map<String, Document> mineMap = iCacheService.getMine();
            list.forEach(e -> {
                Document document = new Document();
                String mineCode = e.getString("_id");
                document.put("unitCode", mineCode);
                document.put("unitName", mineMap.get(mineCode).getString("name"));
                document.put("alarmNum", e.getInteger("count"));
                dataList.add(document);
            });
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", dataList);
        map.put("token", token);
        map.put("category", "1");
        RestTemplateUtils.PostJson(url, map);
    }
}