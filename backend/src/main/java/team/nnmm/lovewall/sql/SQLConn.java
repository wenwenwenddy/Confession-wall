package team.nnmm.lovewall.sql;

import java.sql.*;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class SQLConn {
    public static Connection conn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/confession_wall";
        String user = "root";
        String password = "cxcxcx4,";
        Connection conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void disConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStmt(PreparedStatement psql) {
        if (psql != null) {
            try {
                psql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRe(ResultSet re) {
        if (re != null) {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public SQLConn() {
    }
}
