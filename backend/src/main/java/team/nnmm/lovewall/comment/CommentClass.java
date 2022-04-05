package team.nnmm.lovewall.comment;

import team.nnmm.lovewall.confession.ConfessionClass;
import team.nnmm.lovewall.sql.SQLConn;

import java.sql.*;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class CommentClass {
    public static String addComment(Connection conn, CommentBean comment) {
        PreparedStatement psql = null;
        ResultSet re = null;
        PreparedStatement psqlUpdate = null;
        String res;
        try {
            String sql = "select * from userdata where username = ?";
            psql = conn.prepareStatement(sql);
            psql.setString(1, comment.getUsername());
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                re.next();
                int uid = re.getInt("uid");
                String resConfession = ConfessionClass.fetchConfession(conn, comment.getConfessionId());
                if ("OK".equals(resConfession)) {
                    java.util.Date date_ = new java.util.Date();
                    Date date = new Date(date_.getTime());
                    String sqlUpdate = "insert into commentdata (uid, username, confessionid, content, date)" + "values(?, ?, ?, ?, ?)";
                    psqlUpdate = conn.prepareStatement(sqlUpdate);
                    psqlUpdate.setInt(1, uid);
                    psqlUpdate.setString(2, comment.getUsername());
                    psqlUpdate.setInt(3, comment.getConfessionId());
                    psqlUpdate.setString(4, comment.getContent());
                    psqlUpdate.setDate(5, date);
                    psqlUpdate.executeUpdate();
                    res = "OK";
                } else {
                    res = "CONFESSION_ERROR";
                }
            } else {
                res = "USERNAME_ERROR";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_ERROR";
        } finally {
            SQLConn.closeStmt(psqlUpdate);
            SQLConn.closeRe(re);
            SQLConn.closeStmt(psql);
        }
    }
}
