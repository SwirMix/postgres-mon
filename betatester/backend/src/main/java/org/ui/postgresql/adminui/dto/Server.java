package org.ui.postgresql.adminui.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "servers")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer serverId;
    @Column
    private String serverName;
    @Column
    private String serverDescription;
    @Column
    private Date serverCreated;
    @Column
    private String[] dbExclude;
    @Column
    private boolean enabled;
    @Column
    private String connstr;
    @Column
    private Integer maxSampleAge;
    @Column
    private Integer lastSampleId;
    @Column
    private Date sizeSmpWndStart;
    @Column
    private Date sizeSmpWndDur;
    @Column
    private Date sizeSmpInterval;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
    }

    public Date getServerCreated() {
        return serverCreated;
    }

    public void setServerCreated(Date serverCreated) {
        this.serverCreated = serverCreated;
    }

    public String[] getDbExclude() {
        return dbExclude;
    }

    public void setDbExclude(String[] dbExclude) {
        this.dbExclude = dbExclude;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConnstr() {
        return connstr;
    }

    public void setConnstr(String connstr) {
        this.connstr = connstr;
    }

    public Integer getMaxSampleAge() {
        return maxSampleAge;
    }

    public void setMaxSampleAge(int maxSampleAge) {
        this.maxSampleAge = maxSampleAge;
    }

    public Integer getLastSampleId() {
        return lastSampleId;
    }

    public void setLastSampleId(int lastSampleId) {
        this.lastSampleId = lastSampleId;
    }

    public Date getSizeSmpWndStart() {
        return sizeSmpWndStart;
    }

    public void setSizeSmpWndStart(Date sizeSmpWndStart) {
        this.sizeSmpWndStart = sizeSmpWndStart;
    }

    public Date getSizeSmpWndDur() {
        return sizeSmpWndDur;
    }

    public void setSizeSmpWndDur(Date sizeSmpWndDur) {
        this.sizeSmpWndDur = sizeSmpWndDur;
    }

    public Date getSizeSmpInterval() {
        return sizeSmpInterval;
    }

    public void setSizeSmpInterval(Date sizeSmpInterval) {
        this.sizeSmpInterval = sizeSmpInterval;
    }
}
