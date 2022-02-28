package org.ui.postgresql.adminui.services;

import org.ui.postgresql.adminui.repositories.ServersRepository;

import java.util.concurrent.Callable;

public abstract class BackgroundTask implements Callable<Object> {

    protected BackGroundTaskDescription backGroundTaskDescription;
    protected ServersRepository serversRepository;

    public BackgroundTask(ServersRepository serversRepository, BackGroundTaskDescription backGroundTaskDescription){
        this.serversRepository = serversRepository;
        this.backGroundTaskDescription = backGroundTaskDescription;
    }

    public abstract void run();

    public enum Status {
        SUCCESS,
        ERROR;
    }
}
