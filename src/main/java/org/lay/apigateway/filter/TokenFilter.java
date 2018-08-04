package org.lay.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Create by Lay
 * 2018-03-31 15:20
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 顺序， 越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 返回值必须是true
     * @return true
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        // 获取当前上下文环境
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = requestContext.getRequest();
        // url中获取token
        String token = request.getParameter("token");
        // 如果token为空
        if (StringUtils.isEmpty(token)) {
            // false 表示不通过.
            requestContext.setSendZuulResponse(false);
            // 状态码 401
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
