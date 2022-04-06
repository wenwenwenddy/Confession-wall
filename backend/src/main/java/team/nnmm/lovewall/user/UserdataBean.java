package team.nnmm.lovewall.user;

import java.io.Serializable;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class UserdataBean implements Serializable {
    private String username;
    private String password;
    private int uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public UserdataBean(String username, String password, int uid) {
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    public UserdataBean() {
    }
}
