package team.nnmm.lovewall.user;

import team.nnmm.lovewall.sql.SQLConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class UserClass {
    public static String login(Connection conn, String username, String password) {
        PreparedStatement psql = null;
        ResultSet re = null;
        String res = null;
        try {
            String sql = "select * from userdata where username = ?";
            psql = conn.prepareStatement(sql);
            psql.setString(1, username);
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                re.next();
                if (password.equals(re.getString("password"))) {
                    res = "OK";
                } else {
                    res = "PASSWORD_ERROR";
                }
            } else {
                res = "USERNAME_ERROR";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_ERROR";
        } finally {
            SQLConn.closeRe(re);
            SQLConn.closeStmt(psql);
        }
    }

    public static String register (Connection conn, String username, String password){
        PreparedStatement psql = null;
        ResultSet re = null;
        String res = null;
        PreparedStatement psqlNew = null;
        try {
            String sql = "select * from userdata where username = ?";
            psql = conn.prepareStatement(sql);
            psql.setString(1, username);
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                res = "USERNAME_ERROR";
            } else {
                String sqlNew = "insert into userdata (username, password)" + "values(?, ?)";
                psqlNew = conn.prepareStatement(sqlNew);
                psqlNew.setString(1, username);
                psqlNew.setString(2, password);
                psqlNew.executeUpdate();
                res = "OK";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_ERROR";
        } finally {
            SQLConn.closeStmt(psqlNew);
            SQLConn.closeRe(re);
            SQLConn.closeStmt(psql);
        }
    }
}