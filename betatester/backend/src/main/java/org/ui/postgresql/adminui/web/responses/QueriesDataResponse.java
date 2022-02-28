package org.ui.postgresql.adminui.web.responses;

/**
 *     "queryId": "60853041881134530",
 *     "query": "SELECT oid as tablespaceid,spcname as tablespacename,pg_catalog.pg_tablespace_location(oid) as tablespacepath,pg_catalog.pg_tablespace_size(oid) as size,$1 as size_delta FROM pg_catalog.pg_tablespace",
 *     "userId": "25497",
 *     "datId": "16384",
 *     "calls": 666,
 *     "total_exec_time": 1227.982541,
 *     "min_exec_time": 1227.982541,
 *     "max_exec_time": 1227.982541,
 *     "mean_exec_time": 1227.982541,
 *     "stddev_exec_time": 1227.982541,
 *     "rows": 34,
 *
 *     "shared_blks_hit": 1,
 *     "shared_blks_read": 2324,
 *     "shared_blks_dirtied": 1233,
 *     "shared_blks_written": 123,
 *     "local_blks_hit": 1,
 *     "local_blks_read": 2324,
 *     "local_blks_dirtied": 1233,
 *     "local_blks_written": 123,
 *     "temp_blks_hit": 1,
 *     "temp_blks_read": 2324,
 *     "temp_blks_dirtied": 1233,
 *     "temp_blks_written": 123,
 *     "blk_read_time": 0,
 *     "blk_write_time": 0,
 *     "wal_records": 0,
 *     "wal_fpi": 0,
 *     "wal_bytes": 0
 */

public class QueriesDataResponse {
    private String queryidMd5;
    private long queryId;
    private String query;
    private long userId;
    private long datId;
    private long calls;
    private float total_exec_time;
    private float min_exec_time;
    private float max_exec_time;
    private float mean_exec_time;
    private float stddev_exec_time;
    private long rows;
    private long shared_blks_hit;
    private long shared_blks_read;
    private long shared_blks_dirtied;
    private long shared_blks_written;
    private long local_blks_hit;
    private long local_blks_read;
    private long local_blks_dirtied;
    private long local_blks_written;
    private long temp_blks_written;
    private long temp_blks_dirtied;

    private float blk_read_time;
    private float blk_write_time;
    private long wal_records;
    private float wal_fpi;
    private long wal_bytes;

    public String getQueryidMd5() {
        return queryidMd5;
    }

    public QueriesDataResponse setQueryidMd5(String queryidMd5) {
        this.queryidMd5 = queryidMd5;
        return this;
    }

    public long getQueryId() {
        return queryId;
    }

    public QueriesDataResponse setQueryId(long queryId) {
        this.queryId = queryId;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public QueriesDataResponse setQuery(String query) {
        this.query = query;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public QueriesDataResponse setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getDatId() {
        return datId;
    }

    public QueriesDataResponse setDatId(long datId) {
        this.datId = datId;
        return this;
    }

    public long getCalls() {
        return calls;
    }

    public QueriesDataResponse setCalls(long calls) {
        this.calls = calls;
        return this;
    }

    public float getTotal_exec_time() {
        return total_exec_time;
    }

    public QueriesDataResponse setTotal_exec_time(float total_exec_time) {
        this.total_exec_time = total_exec_time;
        return this;
    }

    public float getMin_exec_time() {
        return min_exec_time;
    }

    public QueriesDataResponse setMin_exec_time(float min_exec_time) {
        this.min_exec_time = min_exec_time;
        return this;
    }

    public float getMax_exec_time() {
        return max_exec_time;
    }

    public QueriesDataResponse setMax_exec_time(float max_exec_time) {
        this.max_exec_time = max_exec_time;
        return this;
    }

    public float getMean_exec_time() {
        return mean_exec_time;
    }

    public QueriesDataResponse setMean_exec_time(float mean_exec_time) {
        this.mean_exec_time = mean_exec_time;
        return this;
    }

    public float getStddev_exec_time() {
        return stddev_exec_time;
    }

    public QueriesDataResponse setStddev_exec_time(float stddev_exec_time) {
        this.stddev_exec_time = stddev_exec_time;
        return this;
    }

    public long getRows() {
        return rows;
    }

    public QueriesDataResponse setRows(long rows) {
        this.rows = rows;
        return this;
    }

    public long getShared_blks_hit() {
        return shared_blks_hit;
    }

    public QueriesDataResponse setShared_blks_hit(long shared_blks_hit) {
        this.shared_blks_hit = shared_blks_hit;
        return this;
    }

    public long getShared_blks_read() {
        return shared_blks_read;
    }

    public QueriesDataResponse setShared_blks_read(long shared_blks_read) {
        this.shared_blks_read = shared_blks_read;
        return this;
    }

    public long getShared_blks_dirtied() {
        return shared_blks_dirtied;
    }

    public QueriesDataResponse setShared_blks_dirtied(long shared_blks_dirtied) {
        this.shared_blks_dirtied = shared_blks_dirtied;
        return this;
    }

    public long getShared_blks_written() {
        return shared_blks_written;
    }

    public QueriesDataResponse setShared_blks_written(long shared_blks_written) {
        this.shared_blks_written = shared_blks_written;
        return this;
    }

    public long getLocal_blks_hit() {
        return local_blks_hit;
    }

    public QueriesDataResponse setLocal_blks_hit(long local_blks_hit) {
        this.local_blks_hit = local_blks_hit;
        return this;
    }

    public long getLocal_blks_read() {
        return local_blks_read;
    }

    public QueriesDataResponse setLocal_blks_read(long local_blks_read) {
        this.local_blks_read = local_blks_read;
        return this;
    }

    public long getLocal_blks_dirtied() {
        return local_blks_dirtied;
    }

    public QueriesDataResponse setLocal_blks_dirtied(long local_blks_dirtied) {
        this.local_blks_dirtied = local_blks_dirtied;
        return this;
    }

    public long getLocal_blks_written() {
        return local_blks_written;
    }

    public QueriesDataResponse setLocal_blks_written(long local_blks_written) {
        this.local_blks_written = local_blks_written;
        return this;
    }

    public long getTemp_blks_dirtied() {
        return temp_blks_dirtied;
    }

    public QueriesDataResponse setTemp_blks_dirtied(long temp_blks_dirtied) {
        this.temp_blks_dirtied = temp_blks_dirtied;
        return this;
    }

    public long getTemp_blks_written() {
        return temp_blks_written;
    }

    public QueriesDataResponse setTemp_blks_written(long temp_blks_written) {
        this.temp_blks_written = temp_blks_written;
        return this;
    }

    public float getBlk_read_time() {
        return blk_read_time;
    }

    public QueriesDataResponse setBlk_read_time(float blk_read_time) {
        this.blk_read_time = blk_read_time;
        return this;
    }

    public float getBlk_write_time() {
        return blk_write_time;
    }

    public QueriesDataResponse setBlk_write_time(float blk_write_time) {
        this.blk_write_time = blk_write_time;
        return this;
    }

    public long getWal_records() {
        return wal_records;
    }

    public QueriesDataResponse setWal_records(long wal_records) {
        this.wal_records = wal_records;
        return this;
    }

    public float getWal_fpi() {
        return wal_fpi;
    }

    public QueriesDataResponse setWal_fpi(float wal_fpi) {
        this.wal_fpi = wal_fpi;
        return this;
    }

    public long getWal_bytes() {
        return wal_bytes;
    }

    public QueriesDataResponse setWal_bytes(long wal_bytes) {
        this.wal_bytes = wal_bytes;
        return this;
    }


    public QueriesDataResponse(){

    }
}
