package zl.com.test.api.common;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static List<String> EXCULDE_COLUMN = Arrays.asList("_id", "uniqueKey", "mineCodeAndDeviceName", "mineCodeAndDeviceNo", "insertTime", "updateTime", "corpCode", "alarmStartTime", "alarmEndTime", "faultStartTime", "faultEndTime");
    public static List<String> TYPE_CONSTANS = Arrays.asList("device", "dev_realtime_alarm", "dev_realtime_value", "dev_his_alarm", "his_value");
    public static String TABLE_HIS_REALTIME = "his_value";

    public interface AlarmQueryType {
        int ALARM = 1;//报警查询
        int ALARM_MONITOR = 2;//报警监测查询
    }

    public interface TimeUnit {
        String YEAR = "YEAR";
        String MONTH = "MON";
        String DAY = "D";
        String HOUR = "H";
        String MINUTE = "M";
        String SECOND = "S";
    }


    public interface DeviceStatus {
        List<String> ALARM_STATUS = Arrays.asList("002", "003", "004");
        List<String> FAULT_STATUS = Arrays.asList("005", "006", "009", "010", "011", "012", "013", "014", "015", "017", "099");
        List<String> ALARM_AND_FAULT_CALIBRATION_STATUS = Arrays.asList("002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "017", "099");
    }

}
