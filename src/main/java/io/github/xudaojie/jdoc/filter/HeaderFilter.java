package io.github.xudaojie.jdoc.filter;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.xudaojie.jdoc.util.TokenUtils;

/**
 * Created by xdj on 2017/4/24.
 */
public class HeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = null;
        HttpServletResponse httpResponse = null;

        if (response instanceof HttpServletResponse) {
            // ReactJS通过Ajax请求时出错
            // http://zjblogs.com/js/Access-Control-Allow-Origin.html
            // http://stackoverflow.com/questions/25727306/request-header-field-access-control-allow-headers-is-not-allowed-by-access-contr
            httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Headers", "X-Access-Token");
        }

        if (request instanceof HttpServletRequest && httpResponse != null) {
            String token = ((HttpServletRequest) request).getHeader("X-Access-Token");
            DecodedJWT decodedJWT = TokenUtils.verify(token);
            if (decodedJWT == null) {
//                httpResponse.sendError(401, "授权失败");
                httpResponse.sendRedirect("error/token");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
