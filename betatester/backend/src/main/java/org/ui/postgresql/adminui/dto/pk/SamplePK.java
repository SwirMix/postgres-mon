package org.ui.postgresql.adminui.dto.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SamplePK implements Serializable {
    @Column
    private Integer sampleId;
    @Column
    private Integer serverId;

    public SamplePK(){

    }

    public SamplePK(Integer sampleId, Integer serverId){
        this.sampleId = sampleId;
        this.serverId = serverId;
    }

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
