package org.lay.apigateway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie工具类
 * Create by Lay
 * 2018-01-16 16:49
 */
public class CookieUtil {

    /**
     * 设置cookie 时长两小时
     * @param response 服务端写入cookie至客户端
     * @param name cookie名称
     * @param value cookie值
     * @param maxAge cookie过期时间
     */
    public static void setCookie(HttpServletResponse response, String name, String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge); // 2小时候过期
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request 当前请求
     * @param name cookie名称
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookies(request);
        if (null != cookieMap) {
            // 如果cookies中包含当前cookie 返回
            if (cookieMap.containsKey(name)) {
                return cookieMap.get(name);
            }
        }
        return null;
    }

    /**
     * 遍历所有cookie并存入map
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookies(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        // request中获取cookie数组
        Cookie[] cookies = request.getCookies();
        // 遍历cookies存入map
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
