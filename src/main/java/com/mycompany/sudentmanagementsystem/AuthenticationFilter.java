
package com.mycompany.sudentmanagementsystem;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        String path = req.getServletPath();

        boolean loggedIn = session != null &&
                session.getAttribute("admin") != null;

        boolean loginRequest = "/login".equals(path);

        // Prevent caching of protected pages
        res.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate");

        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);

        if (loggedIn || loginRequest) {

            chain.doFilter(request, response);

        } else {

            res.sendRedirect(
                    req.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
    }
}
