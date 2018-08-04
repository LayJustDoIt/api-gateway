package org.lay.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * Create by Lay
 * 2018-03-31 16:45
 */
@Component
public class AddResponseHeaderFilter extends ZuulFilter {

    /**
     * 返回类型为post
     * @return POST_TYPE
     */
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        /** 获取请求上下文 */
        RequestContext requestContext = RequestContext.getCurrentContext();
        /** 获取response */
        HttpServletResponse response = requestContext.getResponse();
        /** 设置返回头 */
        response.setHeader("X-Foo", UUID.randomUUID().toString());
        return null;
    }
}
