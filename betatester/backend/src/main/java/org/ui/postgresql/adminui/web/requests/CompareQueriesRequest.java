package org.ui.postgresql.adminui.web.requests;


public class CompareQueriesRequest {
    private long firstServerId;
    private long secondServerId;
    private long firstServerIdSample;
    private long secondServerIdSample;

    public long getFirstServerId() {
        return firstServerId;
    }

    public CompareQueriesRequest setFirstServerId(long firstServerId) {
        this.firstServerId = firstServerId;
        return this;
    }

    public long getSecondServerId() {
        return secondServerId;
    }

    public CompareQueriesRequest setSecondServerId(long secondServerId) {
        this.secondServerId = secondServerId;
        return this;
    }

    public long getFirstServerIdSample() {
        return firstServerIdSample;
    }

    public CompareQueriesRequest setFirstServerIdSample(long firstServerIdSample) {
        this.firstServerIdSample = firstServerIdSample;
        return this;
    }

    public long getSecondServerIdSample() {
        return secondServerIdSample;
    }

    public CompareQueriesRequest setSecondServerIdSample(long secondServerIdSample) {
        this.secondServerIdSample = secondServerIdSample;
        return this;
    }
}
