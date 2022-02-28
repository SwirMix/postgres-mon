package org.ui.postgresql.adminui.web.responses;

import org.ui.postgresql.adminui.dto.ServerSample;

import java.util.List;

public class SampleQueries {
    private ServerSample serverSample;
    /**
        Long - queryId
        QueriesDataResponse - queryData
     **/
    private List<QueriesDataResponse> queries;

    public ServerSample getServerSample() {
        return serverSample;
    }

    public SampleQueries setServerSample(ServerSample serverSample) {
        this.serverSample = serverSample;
        return this;
    }

    public List<QueriesDataResponse> getQueries() {
        return queries;
    }

    public SampleQueries setQueries(List<QueriesDataResponse> queries) {
        this.queries = queries;
        return this;
    }
}
