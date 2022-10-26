package ru.itmo.lab_2.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "CheckURLFilter",
        value = "/*")
public class CheckURLFilter implements Filter {
    private static final String CONTENT_TYPE_TEXT = "text/plain";
    private static final String ENCODING = "UTF-8";
    private static final String MESSAGE_ABOUT_WRONG_URL = "Вам тут не рады(";

    @Override
    public void init(FilterConfig config) throws ServletException {
        Filter.super.init(config);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String servletPath = req.getServletPath();

        if (servletPath.equals("/area-check") || servletPath.equals("/send-table") ||
                servletPath.equals("/set-cookie")) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType( CONTENT_TYPE_TEXT );
            response.setCharacterEncoding( ENCODING );
            PrintWriter out = response.getWriter();
            out.println( MESSAGE_ABOUT_WRONG_URL );
            out.close();
        } else {
            chain.doFilter(request, response);
        }
    }
}
