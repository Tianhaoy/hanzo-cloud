package com.hanzo.client.filter;

import com.hanzo.client.util.SecurityUtil;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author thy
 * @Date 2020/11/17 11:40
 * @Description:
 */
public class AfterCsrfFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("This is a filter");
        SecurityUtil.getUsername((HttpServletRequest) servletRequest);
        // 继续调用 Filter 链
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

