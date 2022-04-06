package team.nnmm.lovewall.comment;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class CommentBean implements Serializable {
    private int id;
    private int uid;
    private String username;
    private int confessionId;
    private String content;
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getConfessionId() {
        return confessionId;
    }

    public void setConfessionId(int confessionId) {
        this.confessionId = confessionId;
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

    public CommentBean(int id, int uid, String username, int confessionId, String content, Date date) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.confessionId = confessionId;
        this.content = content;
        this.date = date;
    }

    public CommentBean() {
    }
}
