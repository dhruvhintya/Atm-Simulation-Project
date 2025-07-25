package com.atmsystem;

import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String accountNumber = req.getParameter("accountNumber");
        String amountStr = req.getParameter("amount");

        res.setContentType("text/plain; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        try {
            double amount = Double.parseDouble(amountStr);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "Dhruv3125");

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT balance FROM users WHERE account_no = ?");
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                if (balance >= amount) {
                    // Update balance
                    PreparedStatement psUpdate = conn.prepareStatement(
                            "UPDATE users SET balance = ? WHERE account_no = ?");
                    psUpdate.setDouble(1, balance - amount);
                    psUpdate.setString(2, accountNumber);
                    psUpdate.executeUpdate();

                    res.getWriter().print("Withdrawn \u20B9" + amount + ". New Balance: \u20B9" + (balance - amount));
;
                } else {
                    res.getWriter().print("Insufficient Balance. Current Balance: ₹" + balance);
                }
            } else {
                res.getWriter().print("Invalid Account Number.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().print("Server error occurred.");
        }
    }
}
