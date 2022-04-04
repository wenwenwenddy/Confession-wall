package team.nnmm.lovewall.confession;

import team.nnmm.lovewall.sql.SQLConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
            SQLConn.closeStmt(psqlUpdate);
            SQLConn.closeRe(re);
            SQLConn.closeStmt(psql);
        }
    }

    public static ArrayList getConfession(Connection conn) {
        PreparedStatement psql = null;
        ResultSet re = null;
        ArrayList res = new ArrayList();
        long t = System.currentTimeMillis();
        Random rand = new Random(t);
        ArrayList<Integer> arr = new ArrayList<>();
        int length = 0;

        try {
            String sql = "select * from contentdata";
            psql = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            re = psql.executeQuery();
            if (re.isBeforeFirst()) {
                // 获取所有表内表白的条数，然后随机获得最多九条表白
                re.last();
                length = re.getRow();
                re.beforeFirst();
                while (arr.size() < Math.min(9, length)) {
                    int rand_ = 9 < length ? rand.nextInt(length - 1) + 1 : length;
                    while(arr.contains(rand_)) {
                        rand_ = rand.nextInt(length - 1) + 1;
                    }
                    arr.add(rand_);
                }
                while (re.next()) {
                    if(arr.contains(re.getRow())) {
                        ConfessionBean res_ = new ConfessionBean();
                        res_.setId(re.getInt("id"));
                        res_.setUid(re.getInt("uid"));
                        res_.setUsername(re.getString("username"));
                        res_.setTarget(re.getString("target"));
                        res_.setContent(re.getString("content"));
                        res_.setDate(re.getDate("date"));
                        res.add(res_);
                    }
                }
            } else {
                res = null;
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
}
