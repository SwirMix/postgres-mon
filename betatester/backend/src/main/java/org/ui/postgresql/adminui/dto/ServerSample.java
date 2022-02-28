package org.ui.postgresql.adminui.dto;

import org.ui.postgresql.adminui.dto.pk.SamplePK;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ServerSample")
@Table(name = "samples")
public class ServerSample {
    @Id
    @EmbeddedId
    private SamplePK samplePK;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="serverId", referencedColumnName="serverId", insertable = false, updatable = false),
    })
    private Server server;
    @Column
    private Date sampleTime;

    public SamplePK getSamplePK() {
        return samplePK;
    }

    public void setSamplePK(SamplePK samplePK) {
        this.samplePK = samplePK;
    }


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Date getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }
}
