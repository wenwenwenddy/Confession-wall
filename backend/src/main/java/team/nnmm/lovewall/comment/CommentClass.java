package team.nnmm.lovewall.comment;

import team.nnmm.lovewall.confession.ConfessionClass;
import team.nnmm.lovewall.sql.SQLConn;

import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList getComment(Connection conn, int confessionId) {
        PreparedStatement psql = null;
        ResultSet re = null;
        ArrayList res = new ArrayList();
        try {
            String sql = "select * from commentdata where confessionId = ?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1, confessionId);
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                while (re.next()) {
                    CommentBean res_ = new CommentBean();
                    res_.setId(re.getInt("id"));
                    res_.setUid(re.getInt("uid"));
                    res_.setUsername(re.getString("username"));
                    res_.setConfessionId(confessionId);
                    res_.setContent(re.getString("content"));
                    res_.setDate(re.getDate("date"));
                    res.add(res_);
                }
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            SQLConn.closeRe(re);
            SQLConn.closeStmt(psql);
        }
    }

    public static String fetchComment(Connection conn, int id) {
        PreparedStatement psql = null;
        ResultSet re = null;
        String res;
        try {
            String sql = "select * from commentdata where id = ?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1, id);
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                re.next();
                res = "OK";
            } else {
                res = "COMMENT_ERROR";
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

    public static String changeComment(Connection conn, CommentBean comment) {
        PreparedStatement psqlUpdate = null;
        String res;
        try {
            res = fetchComment(conn, comment.getId());
            if ("OK".equals(res)) {
                java.util.Date date_ = new java.util.Date();
                Date date = new Date(date_.getTime());
                String sqlUpdate = "update commentdata set `content` = ?, `date` = ? where id = ?";
                psqlUpdate = conn.prepareStatement(sqlUpdate);
                psqlUpdate.setString(1, comment.getContent());
                psqlUpdate.setDate(2, date);
                psqlUpdate.setInt(3, comment.getId());
                psqlUpdate.executeUpdate();
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL_ERROR";
        } finally {
            SQLConn.closeStmt(psqlUpdate);
        }
    }
}
