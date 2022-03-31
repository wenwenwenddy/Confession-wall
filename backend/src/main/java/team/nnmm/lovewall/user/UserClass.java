package team.nnmm.lovewall.user;

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
                if (re.getString("password") == password) {
                    res = "success";
                } else {
                    res = "wrong password";
                }
            } else {
                res = "wrong username";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } finally {
            closeRe(re);
            closeStmt(psql);
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
                res = "used username";
            } else {
                String sqlNew = "insert into userdata (username, password)" + "values(?, ?)";
                psqlNew = conn.prepareStatement(sqlNew);
                psqlNew.setString(1, username);
                psqlNew.setString(2, password);
                psqlNew.executeUpdate();
                res = "success";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } finally {
            closeStmt(psqlNew);
            closeRe(re);
            closeStmt(psql);
        }
    }

    private static void closeStmt(PreparedStatement psql) {
        if (psql != null) {
            try {
                psql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeRe(ResultSet re) {
        if (re != null) {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}