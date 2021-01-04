package zl.com.test.api.config;

import zl.com.test.api.component.InterfaceAuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private InterfaceAuthCheckInterceptor interfaceAuthCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interfaceAuthCheckInterceptor).excludePathPatterns("/footStone/devRealTimeAlarm/getAlarmAndFaultTypePieData","/footStone/devRealTimeAlarm/getAlarmAndFaultTypeLineData").addPathPatterns("/**");
    }
}
