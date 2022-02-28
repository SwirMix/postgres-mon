package org.ui.postgresql.adminui.web.responses;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 	ss.server_id,
 * 	ss.sample_id,
 * 	ss.userid,
 * 	ss.datid,
 * 	ss.queryid,
 * 	ss.queryid_md5,
 *
 * 	sl.query,
 *
 * 	ss.plans,
 * 	ss.total_plan_time,
 * 	ss.min_plan_time,
 * 	ss.max_plan_time,
 * 	ss.mean_plan_time,
 * 	ss.stddev_plan_time,
 * 	ss.calls,
 * 	ss.total_exec_time,
 *
 * 	ss.min_exec_time,
 * 	ss.max_exec_time,
 * 	ss.mean_exec_time,
 *
 * 	ss.rows,
 *
 * 	ss.shared_blks_read,
 * 	ss.shared_blks_dirtied,
 * 	ss.shared_blks_written,
 *
 * 	ss.local_blks_hit,
 * 	ss.local_blks_read,
 * 	ss.local_blks_dirtied,
 *
 * 	ss.local_blks_written,
 * 	ss.temp_blks_read,
 * 	ss.temp_blks_written,
 *
 * 	ss.blk_read_time,
 * 	ss.blk_write_time,
 * 	ss.wal_records,
 * 	ss.wal_fpi,
 * 	ss.wal_bytes
 */
public class QueriesData {
    private int serverid;
    private int sampleid;
    private BigInteger userId;
    private BigInteger datId;
    private BigInteger queryId;
    private String queryidMd5;
    private String query;
    private BigInteger plans;
    private Double total_plan_time;
    private Double min_plan_time;
    private Double max_plan_time;
    private Double mean_plan_time;
    private Double stddev_plan_time;
    private BigInteger calls;
    private Double total_exec_time;
    private Double min_exec_time;
    private Double max_exec_time;
    private Double mean_exec_time;
    private BigInteger rows;
    private BigInteger shared_blks_read;
    private BigInteger shared_blks_dirtied;
    private BigInteger shared_blks_written;
    private BigInteger local_blks_hit;
    private BigInteger local_blks_read;
    private BigInteger local_blks_dirtied;
    private BigInteger local_blks_written;
    private BigInteger temp_blks_read;
    private BigInteger temp_blks_written;
    private Double blk_read_time;
    private BigInteger blk_write_time;
    private Double wal_records;
    private BigInteger wal_fpi;
    private BigInteger wal_bytes;
    private BigDecimal shared_blks_hit;
    private BigInteger stddev_exec_time;

    public BigInteger getStddev_exec_time() {
        return stddev_exec_time;
    }

    public QueriesData setStddev_exec_time(BigInteger stddev_exec_time) {
        this.stddev_exec_time = stddev_exec_time;
        return this;
    }

    public BigDecimal getShared_blks_hit() {
        return shared_blks_hit;
    }

    public QueriesData setShared_blks_hit(BigDecimal shared_blks_hit) {
        this.shared_blks_hit = shared_blks_hit;
        return this;
    }

    public QueriesData(){

    }

    public QueriesData(Object[] info){
        this.serverid = (Integer) info[0];
        this.sampleid = (int) info[1];
        this.userId = (BigInteger) info[2];
        this.datId = (BigInteger) info[3];
        this.queryId = (BigInteger) info[4];
        this.queryidMd5 = (String) info[5];
        this.query = (String) info[6];
        this.plans = (BigInteger) info[7];
        this.total_plan_time = (Double) info[8];
        this.min_plan_time = (Double) info[9];
        this.max_plan_time = (Double) info[10];
        this.mean_plan_time = (Double) info[11];
        this.stddev_plan_time = (Double) info[12];
        this.calls = (BigInteger) info[13];
        this.total_exec_time = (Double) info[14];
        this.min_exec_time = (Double) info[15];
        this.max_exec_time = (Double) info[16];
        this.mean_exec_time = (Double) info[17];
        this.rows = (BigInteger) info[18];
        this.shared_blks_read = (BigInteger) info[19];
        this.shared_blks_dirtied = (BigInteger) info[20];
        this.shared_blks_written = (BigInteger) info[21];
        this.local_blks_hit = (BigInteger) info[22];
        this.local_blks_read = (BigInteger) info[23];
        this.local_blks_dirtied = (BigInteger) info[24];
        this.local_blks_written = (BigInteger) info[25];
        this.temp_blks_read = (BigInteger) info[26];
        this.blk_write_time = (BigInteger) info[27];
        this.blk_read_time = (Double) info[28];
        this.wal_records = (Double) info[29];
        this.wal_fpi = (BigInteger) info[30];
        this.wal_bytes = (BigInteger) info[31];
        this.shared_blks_hit = (BigDecimal) info[32];
        this.stddev_exec_time = (BigInteger) info[33];

    }

    public int getServerid() {
        return serverid;
    }

    public QueriesData setServerid(int serverid) {
        this.serverid = serverid;
        return this;
    }

    public int getSampleid() {
        return sampleid;
    }

    public QueriesData setSampleid(int sampleid) {
        this.sampleid = sampleid;
        return this;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public QueriesData setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public BigInteger getDatId() {
        return datId;
    }

    public QueriesData setDatId(BigInteger datId) {
        this.datId = datId;
        return this;
    }

    public BigInteger getQueryId() {
        return queryId;
    }

    public QueriesData setQueryId(BigInteger queryId) {
        this.queryId = queryId;
        return this;
    }

    public String getQueryidMd5() {
        return queryidMd5;
    }

    public QueriesData setQueryidMd5(String queryidMd5) {
        this.queryidMd5 = queryidMd5;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public QueriesData setQuery(String query) {
        this.query = query;
        return this;
    }

    public BigInteger getPlans() {
        return plans;
    }

    public QueriesData setPlans(BigInteger plans) {
        this.plans = plans;
        return this;
    }

    public Double getTotal_plan_time() {
        return total_plan_time;
    }

    public QueriesData setTotal_plan_time(Double total_plan_time) {
        this.total_plan_time = total_plan_time;
        return this;
    }

    public Double getMin_plan_time() {
        return min_plan_time;
    }

    public QueriesData setMin_plan_time(Double min_plan_time) {
        this.min_plan_time = min_plan_time;
        return this;
    }

    public Double getMax_plan_time() {
        return max_plan_time;
    }

    public QueriesData setMax_plan_time(Double max_plan_time) {
        this.max_plan_time = max_plan_time;
        return this;
    }

    public Double getMean_plan_time() {
        return mean_plan_time;
    }

    public QueriesData setMean_plan_time(Double mean_plan_time) {
        this.mean_plan_time = mean_plan_time;
        return this;
    }

    public Double getStddev_plan_time() {
        return stddev_plan_time;
    }

    public QueriesData setStddev_plan_time(Double stddev_plan_time) {
        this.stddev_plan_time = stddev_plan_time;
        return this;
    }

    public BigInteger getCalls() {
        return calls;
    }

    public QueriesData setCalls(BigInteger calls) {
        this.calls = calls;
        return this;
    }

    public Double getTotal_exec_time() {
        return total_exec_time;
    }

    public QueriesData setTotal_exec_time(Double total_exec_time) {
        this.total_exec_time = total_exec_time;
        return this;
    }

    public Double getMin_exec_time() {
        return min_exec_time;
    }

    public QueriesData setMin_exec_time(Double min_exec_time) {
        this.min_exec_time = min_exec_time;
        return this;
    }

    public Double getMax_exec_time() {
        return max_exec_time;
    }

    public QueriesData setMax_exec_time(Double max_exec_time) {
        this.max_exec_time = max_exec_time;
        return this;
    }

    public Double getMean_exec_time() {
        return mean_exec_time;
    }

    public QueriesData setMean_exec_time(Double mean_exec_time) {
        this.mean_exec_time = mean_exec_time;
        return this;
    }

    public BigInteger getRows() {
        return rows;
    }

    public QueriesData setRows(BigInteger rows) {
        this.rows = rows;
        return this;
    }

    public Double getBlk_read_time() {
        return blk_read_time;
    }

    public QueriesData setBlk_read_time(Double blk_read_time) {
        this.blk_read_time = blk_read_time;
        return this;
    }

    public BigInteger getBlk_write_time() {
        return blk_write_time;
    }

    public QueriesData setBlk_write_time(BigInteger blk_write_time) {
        this.blk_write_time = blk_write_time;
        return this;
    }

    public BigInteger getShared_blks_read() {
        return shared_blks_read;
    }

    public QueriesData setShared_blks_read(BigInteger shared_blks_read) {
        this.shared_blks_read = shared_blks_read;
        return this;
    }

    public BigInteger getShared_blks_dirtied() {
        return shared_blks_dirtied;
    }

    public QueriesData setShared_blks_dirtied(BigInteger shared_blks_dirtied) {
        this.shared_blks_dirtied = shared_blks_dirtied;
        return this;
    }

    public BigInteger getShared_blks_written() {
        return shared_blks_written;
    }

    public QueriesData setShared_blks_written(BigInteger shared_blks_written) {
        this.shared_blks_written = shared_blks_written;
        return this;
    }

    public BigInteger getLocal_blks_hit() {
        return local_blks_hit;
    }

    public QueriesData setLocal_blks_hit(BigInteger local_blks_hit) {
        this.local_blks_hit = local_blks_hit;
        return this;
    }

    public BigInteger getLocal_blks_read() {
        return local_blks_read;
    }

    public QueriesData setLocal_blks_read(BigInteger local_blks_read) {
        this.local_blks_read = local_blks_read;
        return this;
    }

    public BigInteger getLocal_blks_dirtied() {
        return local_blks_dirtied;
    }

    public QueriesData setLocal_blks_dirtied(BigInteger local_blks_dirtied) {
        this.local_blks_dirtied = local_blks_dirtied;
        return this;
    }

    public BigInteger getLocal_blks_written() {
        return local_blks_written;
    }

    public QueriesData setLocal_blks_written(BigInteger local_blks_written) {
        this.local_blks_written = local_blks_written;
        return this;
    }

    public BigInteger getTemp_blks_read() {
        return temp_blks_read;
    }

    public QueriesData setTemp_blks_read(BigInteger temp_blks_read) {
        this.temp_blks_read = temp_blks_read;
        return this;
    }

    public BigInteger getTemp_blks_written() {
        return temp_blks_written;
    }

    public QueriesData setTemp_blks_written(BigInteger temp_blks_written) {
        this.temp_blks_written = temp_blks_written;
        return this;
    }

    public Double getWal_records() {
        return wal_records;
    }

    public QueriesData setWal_records(Double wal_records) {
        this.wal_records = wal_records;
        return this;
    }

    public BigInteger getWal_fpi() {
        return wal_fpi;
    }

    public QueriesData setWal_fpi(BigInteger wal_fpi) {
        this.wal_fpi = wal_fpi;
        return this;
    }

    public BigInteger getWal_bytes() {
        return wal_bytes;
    }

    public QueriesData setWal_bytes(BigInteger wal_bytes) {
        this.wal_bytes = wal_bytes;
        return this;
    }
}
