package org.ui.postgresql.adminui.dto;

import org.ui.postgresql.adminui.dto.pk.SamplePK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "sample")
@Table(name = "samples")
public class Sample  implements Serializable {
    @Id
    @EmbeddedId
    private SamplePK samplePK;
    @Column
    private Date sampleTime;

    public SamplePK getSamplePK() {
        return samplePK;
    }

    public void setSamplePK(SamplePK samplePK) {
        this.samplePK = samplePK;
    }

    public Date getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }


}
