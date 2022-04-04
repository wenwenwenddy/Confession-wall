package team.nnmm.lovewall.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import team.nnmm.lovewall.sql.SQLConn;
import team.nnmm.lovewall.user.UserClass;
import team.nnmm.lovewall.user.UserdataBean;

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
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
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
        String res = UserClass.register(conn, username, password);
        SQLConn.disConn(conn);

        MessageBean jsonOut = new MessageBean(res, new ArrayList<>());
        out.print(OM.writeValueAsString(jsonOut));
    }

    public RegisterServlet() {
    }
}
