package team.nnmm.lovewall.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import team.nnmm.lovewall.confession.ConfessionBean;
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
public class GetConfessionServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        ObjectMapper OM = new ObjectMapper();
        ServletOutputStream out = resp.getOutputStream();
        DataBean jsonOut = new DataBean();

        Connection conn = SQLConn.conn();
        ArrayList res = ConfessionClass.getConfession(conn);
        SQLConn.disConn(conn);

        if(res != null) {
            jsonOut.setLength(res.size());
            jsonOut.setData(res);
            jsonOut.setMessage("OK");
        } else {
            jsonOut.setLength(0);
            jsonOut.setData(new ArrayList<>());
            jsonOut.setMessage("SQL_ERROR");
        }
        out.print(OM.writeValueAsString(jsonOut));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        ObjectMapper OM = new ObjectMapper();
        ConfessionBean jsonIn = OM.readValue(req.getInputStream(), ConfessionBean.class);
        DataBean jsonOut = new DataBean();
        ServletOutputStream out = resp.getOutputStream();

        Connection conn = SQLConn.conn();
        ArrayList res = ConfessionClass.getConfession(conn, jsonIn.getUsername());
        SQLConn.disConn(conn);

        if(res != null && res.size() != 0) {
            jsonOut.setLength(res.size());
            jsonOut.setData(res);
            jsonOut.setMessage("OK");
        } else if(res.size() == 0) {
            jsonOut.setLength(0);
            jsonOut.setData(new ArrayList<>());
            jsonOut.setMessage("USERNAME_ERROR");
        } else {
            jsonOut.setLength(0);
            jsonOut.setData(new ArrayList<>());
            jsonOut.setMessage("SQL_ERROR");
        }
        out.print(OM.writeValueAsString(jsonOut));
    }

    public GetConfessionServlet() {
    }
}