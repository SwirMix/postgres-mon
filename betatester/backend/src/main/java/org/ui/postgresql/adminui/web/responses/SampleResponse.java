package org.ui.postgresql.adminui.web.responses;

import java.util.Date;
import java.util.UUID;

/**
 *   {
 *   "sample_id": 2,
 *   "server_id": 1,
 *   "server_name": "PostgreSQL SE [SHRS]",
 *   "create_date": "2022-02-04 22:19:39.893 +0300",
 *   "sample_time": "2022-02-04 22:19:39.893 +0300"
 *   },
 */
public class SampleResponse {
    private String uuid = UUID.randomUUID().toString();
    private Integer sample_id;
    private Integer server_id;
    private String server_name;
    private Date create_date;
    private Date sample_time;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public SampleResponse(){

    }

    public SampleResponse(Integer sample_id, Integer server_id, String server_name, Date create_date, Date sample_time){
        this.create_date = create_date;
        this.sample_id = sample_id;
        this.server_id = server_id;
        this.server_name = server_name;
        this.sample_time = sample_time;
    }

    public Integer getSample_id() {
        return sample_id;
    }

    public void setSample_id(Integer sample_id) {
        this.sample_id = sample_id;
    }

    public Integer getServer_id() {
        return server_id;
    }

    public void setServer_id(Integer server_id) {
        this.server_id = server_id;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getSample_time() {
        return sample_time;
    }

    public void setSample_time(Date sample_time) {
        this.sample_time = sample_time;
    }
}
