package org.ui.postgresql.adminui.web.responses;

import org.ui.postgresql.adminui.dto.InstanceInterval;

import java.util.List;

public class CheckReportResponse {
    private String name;
    private List<InstanceInterval> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InstanceInterval> getData() {
        return data;
    }

    public void setData(List<InstanceInterval> data) {
        this.data = data;
    }
}
