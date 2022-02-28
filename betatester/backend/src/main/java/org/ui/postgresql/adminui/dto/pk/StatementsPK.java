package org.ui.postgresql.adminui.dto.pk;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StatementsPK implements Serializable {
    @Column(name = "server_id")
    private long serverId;
    @Column(name = "sample_id")
    private long sampleId;
    @Column(name = "datid")
    private long datid;
    @Column(name = "userid")
    private long userId;
    @Column(name = "queryid")
    private long queryid;

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public long getSampleId() {
        return sampleId;
    }

    public void setSampleId(long sampleId) {
        this.sampleId = sampleId;
    }

    public long getDatid() {
        return datid;
    }

    public void setDatid(long datid) {
        this.datid = datid;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getQueryid() {
        return queryid;
    }

    public void setQueryid(long queryid) {
        this.queryid = queryid;
    }
}
