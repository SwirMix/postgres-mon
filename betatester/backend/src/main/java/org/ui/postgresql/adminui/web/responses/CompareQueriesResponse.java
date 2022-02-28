package org.ui.postgresql.adminui.web.responses;

import org.ui.postgresql.adminui.dto.ServerSample;

public class CompareQueriesResponse {
    private long queryId;
    private QueriesDataResponse firstSampleQuery;
    private QueriesDataResponse secondSampleQuery;
    private ServerSample firstServer;
    private ServerSample secondServer;

    private boolean hit;

    public boolean isHit() {
        return hit;
    }

    public CompareQueriesResponse setHit(boolean hit) {
        this.hit = hit;
        return this;
    }

    public long getQueryId() {
        return queryId;
    }

    public CompareQueriesResponse setQueryId(long queryId) {
        this.queryId = queryId;
        return this;
    }

    public QueriesDataResponse getFirstSampleQuery() {
        return firstSampleQuery;
    }

    public CompareQueriesResponse setFirstSampleQuery(QueriesDataResponse firstSampleQuery) {
        this.firstSampleQuery = firstSampleQuery;
        return this;
    }

    public QueriesDataResponse getSecondSampleQuery() {
        return secondSampleQuery;
    }

    public CompareQueriesResponse setSecondSampleQuery(QueriesDataResponse secondSampleQuery) {
        this.secondSampleQuery = secondSampleQuery;
        return this;
    }

    public ServerSample getFirstServer() {
        return firstServer;
    }

    public CompareQueriesResponse setFirstServer(ServerSample firstServer) {
        this.firstServer = firstServer;
        return this;
    }

    public ServerSample getSecondServer() {
        return secondServer;
    }

    public CompareQueriesResponse setSecondServer(ServerSample secondServer) {
        this.secondServer = secondServer;
        return this;
    }
}
