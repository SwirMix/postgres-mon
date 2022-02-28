package org.ui.postgresql.adminui.dto;

import org.ui.postgresql.adminui.dto.pk.StatementsPK;
import org.ui.postgresql.adminui.dto.pk.StmtPK;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sample_statements")
@Entity(name = "SampleStatement")
public class SampleStatement {
    @Id
    @EmbeddedId
    private StatementsPK primaryKey;
    @Column(name = "plans")
    private long plans;
    @Column(name = "total_plan_time")
    private float totalPlanTime;
    @Column(name = "min_plan_time")
    private float minPlanTime;
    @Column(name = "max_plan_time")
    private float maxPlanTime;
    @Column(name = "mean_plan_time")
    private float meanPlanTime;

    @Column(name = "queryid_md5")
    private String queryidMd5;

    @Column(name = "stddev_plan_time")
    private float stddevPlanTime;
    @Column(name = "calls")
    private int calls;
    @Column(name = "total_exec_time")
    private float totalExecTime;
    @Column(name = "min_exec_time")
    private float minExecTime;
    @Column(name = "max_exec_time")
    private float maxExecTime;
    @Column(name = "mean_exec_time")
    private float meanExecTime;
    @Column(name = "stddev_exec_time")
    private float stddevExecTime;
    @Column(name = "rows")
    private int rows;
    @Column(name = "shared_blks_hit")
    private int sharedBlksHit;
    @Column(name = "shared_blks_read")
    private int sharedBlksRead;
    @Column(name = "shared_blks_dirtied")
    private int sharedBlksDirtied;
    @Column(name = "shared_blks_written")
    private int sharedBlksWritten;

    @Column(name = "local_blks_hit")
    private int localBlksHit;
    @Column(name = "local_blks_read")
    private int localBlksRead;
    @Column(name = "local_blks_dirtied")
    private int localBlksDirtied;
    @Column(name = "local_blks_written")
    private int localBlksWritten;

    @Column(name = "temp_blks_read")
    private int tempBlksDirtied;
    @Column(name = "temp_blks_written")
    private int tempBlksWritten;

    @Column(name = "blk_read_time")
    private int blkReadTime;
    @Column(name = "blk_write_time")
    private int blkWriteTime;

    @Column(name = "wal_records")
    private int walRecords;
    @Column(name = "wal_fpi")
    private int walFpi;
    @Column(name = "wal_bytes")
    private int walBytes;

    public String getQueryidMd5() {
        return queryidMd5;
    }

    public void setQueryidMd5(String queryidMd5) {
        this.queryidMd5 = queryidMd5;
    }

    public int getSharedBlksRead() {
        return sharedBlksRead;
    }

    public void setSharedBlksRead(int sharedBlksRead) {
        this.sharedBlksRead = sharedBlksRead;
    }

    public int getSharedBlksDirtied() {
        return sharedBlksDirtied;
    }

    public void setSharedBlksDirtied(int sharedBlksDirtied) {
        this.sharedBlksDirtied = sharedBlksDirtied;
    }

    public int getSharedBlksWritten() {
        return sharedBlksWritten;
    }

    public void setSharedBlksWritten(int sharedBlksWritten) {
        this.sharedBlksWritten = sharedBlksWritten;
    }

    public int getLocalBlksHit() {
        return localBlksHit;
    }

    public void setLocalBlksHit(int localBlksHit) {
        this.localBlksHit = localBlksHit;
    }

    public int getLocalBlksRead() {
        return localBlksRead;
    }

    public void setLocalBlksRead(int localBlksRead) {
        this.localBlksRead = localBlksRead;
    }

    public int getLocalBlksDirtied() {
        return localBlksDirtied;
    }

    public void setLocalBlksDirtied(int localBlksDirtied) {
        this.localBlksDirtied = localBlksDirtied;
    }

    public int getLocalBlksWritten() {
        return localBlksWritten;
    }

    public void setLocalBlksWritten(int localBlksWritten) {
        this.localBlksWritten = localBlksWritten;
    }

    public int getTempBlksDirtied() {
        return tempBlksDirtied;
    }

    public void setTempBlksDirtied(int tempBlksDirtied) {
        this.tempBlksDirtied = tempBlksDirtied;
    }

    public int getTempBlksWritten() {
        return tempBlksWritten;
    }

    public void setTempBlksWritten(int tempBlksWritten) {
        this.tempBlksWritten = tempBlksWritten;
    }

    public int getBlkReadTime() {
        return blkReadTime;
    }

    public void setBlkReadTime(int blkReadTime) {
        this.blkReadTime = blkReadTime;
    }

    public int getBlkWriteTime() {
        return blkWriteTime;
    }

    public void setBlkWriteTime(int blkWriteTime) {
        this.blkWriteTime = blkWriteTime;
    }

    public int getWalRecords() {
        return walRecords;
    }

    public void setWalRecords(int walRecords) {
        this.walRecords = walRecords;
    }

    public int getWalFpi() {
        return walFpi;
    }

    public void setWalFpi(int walFpi) {
        this.walFpi = walFpi;
    }

    public int getWalBytes() {
        return walBytes;
    }

    public void setWalBytes(int walBytes) {
        this.walBytes = walBytes;
    }

    public StatementsPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(StatementsPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public long getPlans() {
        return plans;
    }

    public void setPlans(long plans) {
        this.plans = plans;
    }

    public float getTotalPlanTime() {
        return totalPlanTime;
    }

    public void setTotalPlanTime(float totalPlanTime) {
        this.totalPlanTime = totalPlanTime;
    }

    public float getMinPlanTime() {
        return minPlanTime;
    }

    public void setMinPlanTime(float minPlanTime) {
        this.minPlanTime = minPlanTime;
    }

    public float getMaxPlanTime() {
        return maxPlanTime;
    }

    public void setMaxPlanTime(float maxPlanTime) {
        this.maxPlanTime = maxPlanTime;
    }

    public float getMeanPlanTime() {
        return meanPlanTime;
    }

    public void setMeanPlanTime(float meanPlanTime) {
        this.meanPlanTime = meanPlanTime;
    }

    public float getStddevPlanTime() {
        return stddevPlanTime;
    }

    public void setStddevPlanTime(float stddevPlanTime) {
        this.stddevPlanTime = stddevPlanTime;
    }

    public int getCalls() {
        return calls;
    }

    public void setCalls(int calls) {
        this.calls = calls;
    }

    public float getTotalExecTime() {
        return totalExecTime;
    }

    public void setTotalExecTime(float totalExecTime) {
        this.totalExecTime = totalExecTime;
    }

    public float getMinExecTime() {
        return minExecTime;
    }

    public void setMinExecTime(float minExecTime) {
        this.minExecTime = minExecTime;
    }

    public float getMaxExecTime() {
        return maxExecTime;
    }

    public void setMaxExecTime(float maxExecTime) {
        this.maxExecTime = maxExecTime;
    }

    public float getMeanExecTime() {
        return meanExecTime;
    }

    public void setMeanExecTime(float meanExecTime) {
        this.meanExecTime = meanExecTime;
    }

    public float getStddevExecTime() {
        return stddevExecTime;
    }

    public void setStddevExecTime(float stddevExecTime) {
        this.stddevExecTime = stddevExecTime;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSharedBlksHit() {
        return sharedBlksHit;
    }

    public void setSharedBlksHit(int sharedBlksHit) {
        this.sharedBlksHit = sharedBlksHit;
    }
}
