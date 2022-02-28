package org.ui.postgresql.adminui.dto;

import java.util.List;

public class InstanceInterval {
    private String x;
    private List<Long> y;


    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public List<Long> getY() {
        return y;
    }

    public void setY(List<Long> y) {
        this.y = y;
    }
}
