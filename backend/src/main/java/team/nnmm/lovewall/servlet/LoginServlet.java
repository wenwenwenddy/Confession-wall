package team.nnmm.lovewall.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import team.nnmm.lovewall.sql.SQLConn;
import team.nnmm.lovewall.user.UserClass;
import team.nnmm.lovewall.user.UserdataBean;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        ObjectMapper OM = new ObjectMapper();
        ServletOutputStream out = resp.getOutputStream();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Connection conn = SQLConn.conn();
        String res = UserClass.login(conn, username, password);
        Cookie cookie = new Cookie("name", username);
        resp.addCookie(cookie);

        SQLConn.disConn(conn);

        MessageBean jsonOut = new MessageBean(res);
        out.print(OM.writeValueAsString(jsonOut));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        ObjectMapper OM = new ObjectMapper();
        UserdataBean jsonIn = OM.readValue(req.getInputStream(), UserdataBean.class);
        ServletOutputStream out = resp.getOutputStream();

        String username = jsonIn.getUsername();
        String password = jsonIn.getPassword();

        Connection conn = SQLConn.conn();
        String res = UserClass.login(conn, username, password);
        Cookie cookie = new Cookie("name", username);
        resp.addCookie(cookie);

        SQLConn.disConn(conn);

        MessageBean jsonOut = new MessageBean(res);
        out.print(OM.writeValueAsString(jsonOut));
    }

    public LoginServlet() {
    }
}
