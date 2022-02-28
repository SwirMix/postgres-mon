package org.ui.postgresql.adminui.web.requests;

import java.io.Serializable;
import java.util.List;

public class CheckReportRequest implements Serializable {
    private List<List<Integer>> samples;

    public List<List<Integer>> getSamples() {
        return samples;
    }

    public void setSamples(List<List<Integer>> samples) {
        this.samples = samples;
    }
}
