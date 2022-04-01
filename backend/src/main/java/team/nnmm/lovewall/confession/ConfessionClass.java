package team.nnmm.lovewall.confession;

import java.sql.*;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class ConfessionClass {
    public static String addConfession(Connection conn, ConfessionBean confession) {
        PreparedStatement psql = null;
        ResultSet re = null;
        PreparedStatement psqlUpdate = null;
        String res = null;
        try {
            String sql = "select * from userdata where username = ?";
            psql = conn.prepareStatement(sql);
            psql.setString(1, confession.getUsername());
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                re.next();
                int uid = re.getInt("uid");
                java.util.Date date_ = new java.util.Date();
                Date date = new Date(date_.getTime());
                String sqlUpdate = "insert into contentdata (uid, username, content, date, target)" + "values(?, ?, ?, ?, ?)";
                psqlUpdate = conn.prepareStatement(sqlUpdate);
                psqlUpdate.setInt(1, uid);
                psqlUpdate.setString(2, confession.getUsername());
                psqlUpdate.setString(3, confession.getContent());
                psqlUpdate.setDate(4, date);
                psqlUpdate.setString(5, confession.getTarget());
                psqlUpdate.executeUpdate();
                res = "OK";
            } else {
                res = "USERNAME_ERROR";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_ERROR";
        } finally {
            closeStmt(psqlUpdate);
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
