package com.bjqg.web.config;

import com.bjqg.web.utils.common.ServletUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务器相关配置
 *
 * @author: lbj
 * @date: 2022/12/6 10:35
 */
@Component
public class ServerConfig {

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     *
     * @return : {@link java.lang.String}
     * @author : lbj
     * @description: <获取完整的请求路径，包括：域名，端口，上下文访问路径>
     * @date : 2022/12/6 10:40
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
