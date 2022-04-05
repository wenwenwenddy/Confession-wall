package team.nnmm.lovewall.servlet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Patrick_Star
 * @version 1.0
 */
public class DataBean extends MessageBean implements Serializable {
    private int length;
    private ArrayList data;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public DataBean(int length, ArrayList data) {
        this.length = length;
        this.data = data;
    }

    public DataBean(String message, int length, ArrayList data) {
        super(message);
        this.length = length;
        this.data = data;
    }

    public DataBean() {
    }
}
