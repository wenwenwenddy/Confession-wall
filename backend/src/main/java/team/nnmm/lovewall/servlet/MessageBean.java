package team.nnmm.lovewall.servlet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class MessageBean implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageBean() {
    }

    public MessageBean(String message) {
        this.message = message;
    }
}