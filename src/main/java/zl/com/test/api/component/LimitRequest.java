package zl.com.test.api.component;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {
    long time() default 1000*30;//限制时间 单位：毫秒
    int count() default 1;//允许访问的次数
}
