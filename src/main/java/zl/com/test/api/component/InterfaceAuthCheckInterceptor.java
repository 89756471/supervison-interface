package zl.com.test.api.component;

import org.springframework.web.servlet.ModelAndView;
import zl.com.test.api.service.impl.CacheSerivceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class InterfaceAuthCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private CacheSerivceImpl cacheSerivce;

    /**
     * 校验请求的token是有权访问数据
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURI();
        if (url.contains("aqjk")) {
            return true;
        }
        Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String token = "";
        String corpCode = "";
        if (null != map) {
            token = (String) map.get("token");
            corpCode = (String) map.get("corpCode");
        }
        if (null == map || map.isEmpty()) {
            token = request.getParameter("token");
            corpCode = request.getParameter("corpCode");
        }
        boolean isAuth = this.isAuth(token, corpCode);
        log.info("公司编码为：{},token：{},IP地址：{},授权是否成功:{}", corpCode, token, request.getRemoteAddr(), isAuth);
        return isAuth;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean isAuth(String token, String corpCode) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        Map<String, Document> map = cacheSerivce.getCorp();
        if (!map.containsKey(corpCode)) {
            return false;
        }
        Document document = map.get(corpCode);
        String key = document.containsKey("key") ? document.getString("key") : "";
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        if (token.equals(key)) {
            return true;
        }
        return false;
    }
}
