package com.atmsystem;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExitServlet")
public class ExitServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Invalidate session if it exists
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Redirect back to index page or login
        res.sendRedirect("index.html");
    }
}
