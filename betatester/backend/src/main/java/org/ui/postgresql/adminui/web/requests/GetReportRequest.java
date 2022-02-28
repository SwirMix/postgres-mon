package org.ui.postgresql.adminui.web.requests;

import java.io.Serializable;
import java.util.List;

public class GetReportRequest implements Serializable {
    private String name;
    private CheckReportRequest samples;
    private List servers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckReportRequest getSamples() {
        return samples;
    }

    public void setSamples(CheckReportRequest samples) {
        this.samples = samples;
    }

    public List<Integer> getServers() {
        return servers;
    }

    public void setServers(List<Integer> servers) {
        this.servers = servers;
    }
}
