package org.ui.postgresql.adminui.web.requests;

import java.util.List;

public class ReportRequest {
    private String name;
    private List<SampleRange> sampleRanges;
    private List<Integer> serverIds;
    private boolean single;
    private boolean compare;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getServerIds() {
        return serverIds;
    }

    public void setServerIds(List<Integer> serverIds) {
        this.serverIds = serverIds;
    }

    public List<SampleRange> getSampleRanges() {
        return sampleRanges;
    }

    public void setSampleRanges(List<SampleRange> sampleRanges) {
        this.sampleRanges = sampleRanges;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public boolean isCompare() {
        return compare;
    }

    public void setCompare(boolean compare) {
        this.compare = compare;
    }

    public class SampleRange {
        private Integer startId;
        private Integer endId;

        public Integer getStartId() {
            return startId;
        }

        public void setStartId(Integer startId) {
            this.startId = startId;
        }

        public Integer getEndId() {
            return endId;
        }

        public void setEndId(Integer endId) {
            this.endId = endId;
        }
    }
}
