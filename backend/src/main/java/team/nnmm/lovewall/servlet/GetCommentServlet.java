package team.nnmm.lovewall.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import team.nnmm.lovewall.comment.CommentBean;
import team.nnmm.lovewall.comment.CommentClass;
import team.nnmm.lovewall.confession.ConfessionClass;
import team.nnmm.lovewall.sql.SQLConn;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class GetCommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        ObjectMapper OM = new ObjectMapper();
        CommentBean jsonIn = OM.readValue(req.getInputStream(), CommentBean.class);
        DataBean jsonOut = new DataBean();
        ServletOutputStream out = resp.getOutputStream();

        Connection conn = SQLConn.conn();
        String resConfession = ConfessionClass.fetchConfession(conn, jsonIn.getConfessionId());
        ArrayList res = new ArrayList();
        if("OK".equals(resConfession)) {
            res = CommentClass.getComment(conn, jsonIn.getConfessionId());
        }
        SQLConn.disConn(conn);

        if("OK".equals(resConfession) & res != null && res.size() != 0) {
            jsonOut.setLength(res.size());
            jsonOut.setData(res);
            jsonOut.setMessage("OK");
        } else if("OK".equals(resConfession) & res.size() == 0) {
            jsonOut.setLength(0);
            jsonOut.setData(new ArrayList<>());
            jsonOut.setMessage("EMPTY");
        } else if("CONFESSION_ERROR".equals(resConfession)){
            jsonOut.setLength(0);
            jsonOut.setData(new ArrayList<>());
            jsonOut.setMessage("CONFESSION_ERROR");
        } else {
            jsonOut.setLength(0);
            jsonOut.setData(new ArrayList<>());
            jsonOut.setMessage("SQL_ERROR");
        }
        out.print(OM.writeValueAsString(jsonOut));
    }
}
