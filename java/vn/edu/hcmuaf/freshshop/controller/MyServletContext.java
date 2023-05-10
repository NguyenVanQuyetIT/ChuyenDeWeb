package vn.edu.hcmuaf.freshshop.controller;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class MyServletContext implements ServletContextAware {
    private static ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        MyServletContext.servletContext = servletContext;
    }

    public static void setAttribute(String key, Object value) {
        servletContext.setAttribute(key, value);
    }

    public static Object getAttribute(String key) {
        return servletContext.getAttribute(key);
    }
}
