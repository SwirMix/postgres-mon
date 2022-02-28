package org.ui.postgresql.adminui.web.responses;

import org.ui.postgresql.adminui.dto.ServerSample;

public class MissQueriesResponse {
    private long queryId;
    private ServerSample serverSample;
    private QueriesDataResponse info;

    public long getQueryId() {
        return queryId;
    }

    public MissQueriesResponse setQueryId(long queryId) {
        this.queryId = queryId;
        return this;
    }

    public ServerSample getServerSample() {
        return serverSample;
    }

    public MissQueriesResponse setServerSample(ServerSample serverSample) {
        this.serverSample = serverSample;
        return this;
    }

    public QueriesDataResponse getInfo() {
        return info;
    }

    public MissQueriesResponse setInfo(QueriesDataResponse info) {
        this.info = info;
        return this;
    }
}
