
package com.mycompany.sudentmanagementsystem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null &&
                session.getAttribute("admin") != null) {

            response.sendRedirect(
                request.getContextPath() + "/students");

            return;
        }

        request.getRequestDispatcher(
            "/WEB-INF/views/login.jsp")
            .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) &&
                "admin123".equals(password)) {

            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);

            session.setAttribute("admin", username);

            session.setMaxInactiveInterval(30 * 60);

            response.sendRedirect(
                request.getContextPath() + "/students");

        } else {

            request.setAttribute("error",
                "Invalid username or password!");

            request.getRequestDispatcher(
                "/WEB-INF/views/login.jsp")
                .forward(request, response);
        }
    }
}
