package com.liba.controller.filter;

import com.liba.model.entity.Role;
import com.liba.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AuthFilter implements Filter {

    private final List<String> adminPaths = Arrays.asList("/index", "/logout", "/books", "/authors", "/create_book",
            "/create_author", "/delete_author", "/delete_book", "/taken_books");
    private final List<String> userPaths = Arrays.asList("/index", "/books", "/authors", "/logout");
    private final List<String> defaultPaths = Arrays.asList("/index", "/login", "/registration", "/authors", "/books", "/create_book");
    private Map<Role, List<String>> allowedPathPatterns = new HashMap<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedPathPatterns.put(Role.USER, userPaths);
        allowedPathPatterns.put(Role.ADMIN, adminPaths);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI().replaceAll(".*/app", "");

        User user = (User) session.getAttribute("user");

        if (Objects.isNull(user)) {
            if (defaultPaths.contains(requestURI)) {
                filterChain.doFilter(request, response);
                return;
            } else {
                response.sendRedirect(request.getContextPath() +
                        request.getServletPath() +
                        "/login");
                return;
            }
        }
        List<String> paths = new ArrayList<>();

        for (Role role : user.getRole()) {
            paths.addAll(allowedPathPatterns.get(role));
        }
        boolean contains = false;
        for (String key : paths) {
            if (requestURI.contains(key)) {
                contains = true;
                filterChain.doFilter(request, response);
                break;
            }
        }
        if (!contains) {
            response.setStatus(403);
            response.sendRedirect("/forbidden.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
