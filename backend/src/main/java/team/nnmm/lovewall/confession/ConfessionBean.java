package team.nnmm.lovewall.confession;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class ConfessionBean implements Serializable {
    private int uid;
    private String username;
    private String content;
    private Date date;
    private String target;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ConfessionBean() {
    }

    public ConfessionBean(int uid, String username, String content, Date date, String target) {
        this.uid = uid;
        this.username = username;
        this.content = content;
        this.date = date;
        this.target = target;
    }
}
