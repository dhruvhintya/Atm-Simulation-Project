package com.atmsystem;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String accountNumber = req.getParameter("accountNumber");
        String pin = req.getParameter("pin");

        res.setContentType("text/plain");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "Dhruv3125");

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM users WHERE account_no = ? AND pin = ?");
            ps.setString(1, accountNumber);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                res.getWriter().print("success");
            } else {
                res.getWriter().print("invalid");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().print("error");
        }
    }
}
