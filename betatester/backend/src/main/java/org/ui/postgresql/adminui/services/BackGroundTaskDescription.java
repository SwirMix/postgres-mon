package org.ui.postgresql.adminui.services;

import java.util.Date;
import java.util.HashMap;

public class BackGroundTaskDescription {
    private String uuid;
    private HashMap params;
    private String name;
    private Date startDate;
    private Date endDate;
    private BackgroundTask.Status status;
    private Object result;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BackGroundTaskDescription(HashMap params, String name){
        this.name = name;
        this.params = params;
    }

    public BackGroundTaskDescription(){

    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public HashMap getParams() {
        return params;
    }

    public void setParams(HashMap params) {
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BackgroundTask.Status getStatus() {
        return status;
    }

    public void setStatus(BackgroundTask.Status status) {
        this.status = status;
    }
}