package io.github.xudaojie.jdoc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xdj on 2017/4/19.
 */
public class CharsetEncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8"); // 控制浏览器行为
        response.setCharacterEncoding("UTF-8"); // 控制输出流格式(getWriter())

        if (response instanceof HttpServletResponse) {
            // ReactJS通过Ajax请求时出错
            // http://zjblogs.com/js/Access-Control-Allow-Origin.html
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "X-Access-Token");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
