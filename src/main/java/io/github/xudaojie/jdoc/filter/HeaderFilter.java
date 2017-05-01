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

import io.github.xudaojie.jdoc.util.TextUtils;
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
        HttpServletRequest httpRequest;
        HttpServletResponse httpResponse = null;

        if (request instanceof HttpServletRequest) {
            httpRequest = (HttpServletRequest) request;
            httpResponse = (HttpServletResponse) response;

            String token = httpRequest.getHeader("X-Access-Token");
            String method = httpRequest.getMethod();
            if (!TextUtils.isEmpty(token)) {
                DecodedJWT decodedJWT = TokenUtils.verify(token);
                if (decodedJWT == null) {
//                httpResponse.sendError(401, "授权失败");
                    httpResponse.sendRedirect("error/token");
                }
            }
//            else if (!TextUtils.equals("GET", method) && !TextUtils.equals("OPTIONS", method)) {
//                // post/upDelete/put 必须传token
//                httpResponse.sendRedirect("error/token");
//            }
            // ReactJS通过Ajax请求时出错
            // http://zjblogs.com/js/Access-Control-Allow-Origin.html
            // http://stackoverflow.com/questions/25727306/request-header-field-access-control-allow-headers-is-not-allowed-by-access-contr
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Headers", "X-Access-Token,Authorization");

        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
