package team.nnmm.lovewall.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import team.nnmm.lovewall.comment.CommentBean;
import team.nnmm.lovewall.comment.CommentClass;
import team.nnmm.lovewall.sql.SQLConn;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class AddCommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        ObjectMapper OM = new ObjectMapper();
        CommentBean jsonIn = OM.readValue(req.getInputStream(), CommentBean.class);
        ServletOutputStream out = resp.getOutputStream();

        Connection conn = SQLConn.conn();
        String res = CommentClass.addComment(conn, jsonIn);
        SQLConn.disConn(conn);

        MessageBean jsonOut = new MessageBean(res);
        out.print(OM.writeValueAsString(jsonOut));
    }

    public AddCommentServlet() {
    }
}
