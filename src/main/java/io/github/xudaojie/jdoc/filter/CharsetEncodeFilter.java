package io.github.xudaojie.jdoc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by xdj on 2017/4/19.
 */
public class CharsetEncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8"); // 控制浏览器行为
        response.setCharacterEncoding("UTF-8"); // 控制输出流格式(getWriter())

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
