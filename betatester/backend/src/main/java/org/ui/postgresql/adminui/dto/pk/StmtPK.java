package org.ui.postgresql.adminui.dto.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StmtPK implements Serializable {
    @Column(name = "server_id")
    private long serverId;
    @Column(name = "queryid_md5")
    private String queryidMd5;

    public String getQueryidMd5() {
        return queryidMd5;
    }

    public void setQueryidMd5(String queryidMd5) {
        this.queryidMd5 = queryidMd5;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }
}
