package com.atmsystem;

import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String accountNumber = req.getParameter("accountNumber");

        res.setContentType("text/plain");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "Dhruv3125");

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT balance FROM users WHERE account_no = ?");
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                res.getWriter().println(balance);
            } else {
                res.getWriter().println("Invalid");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error");
        }
    }
}
