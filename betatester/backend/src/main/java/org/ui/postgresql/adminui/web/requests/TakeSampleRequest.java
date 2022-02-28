package org.ui.postgresql.adminui.web.requests;

import java.util.List;

public class TakeSampleRequest {
    private List<Integer> servers;

    public List<Integer> getServers() {
        return servers;
    }

    public void setServers(List<Integer> servers) {
        this.servers = servers;
    }
}
