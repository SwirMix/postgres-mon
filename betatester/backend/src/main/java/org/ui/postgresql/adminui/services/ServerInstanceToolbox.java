package org.ui.postgresql.adminui.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ui.postgresql.adminui.repositories.ServersRepository;
import org.ui.postgresql.adminui.web.requests.InstanceRequest;
import org.ui.postgresql.adminui.web.requests.TakeSampleRequest;
import org.ui.postgresql.adminui.web.responses.InstanceResponse;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ServerInstanceToolbox implements ToolboxInterface{
    private static final String DB_URL = "jdbc:postgresql://%s:%s/%s"; //host:port/db
    private Logger logger = LoggerFactory.getLogger(ServerInstanceToolbox.class);
    private ConcurrentHashMap<String, Future> backgroundTasks = new ConcurrentHashMap();
    private static final int MAX_BACKGROUND_SIZE = 3000;
    private ExecutorService executors = Executors.newFixedThreadPool(30);
    @Autowired
    private ServersRepository serversRepository;
    @Autowired
    private PersistenceReportRepository persistenceReportRepository;

    @Override
    public InstanceResponse checkInstance(InstanceRequest request) throws ClassNotFoundException {
        InstanceResponse response = new InstanceResponse();
        response.setHost(request.getHost());
        response.setPort(request.getPort());
        String connectionString = String.format(
                DB_URL,
                request.getHost(),
                request.getPort(),
                request.getDatabase()
        );
        logger.info(connectionString);
        Class.forName("org.postgresql.Driver");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionString, request.getUser(), request.getPassword());
        } catch (SQLException e) {
            logger.error("Connection Failed: " + connectionString);
            e.printStackTrace();
        }

        if (connection != null) {
            response.setStatus(true);
            logger.info("You successfully connected to database now");
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(false);
            logger.info("Failed to make connection to database");
        }

        return response;
    }

    @Override
    public List<String> takeSample(TakeSampleRequest takeSampleRequest) {
        List<String> tasks = new ArrayList<>();
        for (Integer serverId : takeSampleRequest.getServers()) {
            String uuid = UUID.randomUUID().toString();
            HashMap params = new HashMap();
            params.put("serverId", serverId);


            if (serversRepository.findById(serverId).isPresent()){
                Future future = executors.submit(new BackgroundTask(serversRepository, new BackGroundTaskDescription(params,"take_sample for serverId: " + serverId)) {
                    @Override
                    public void run() {

                    }

                    @Override
                    public BackGroundTaskDescription call() throws Exception {
                        try {
                            super.backGroundTaskDescription.setUuid(uuid);
                            super.backGroundTaskDescription.setStartDate(new Date());
                            try {
                                super.backGroundTaskDescription.setResult(super.serversRepository.takeSampleByServerId(serverId));
                                super.backGroundTaskDescription.setStatus(Status.SUCCESS);
                            } catch (Exception e){
                                super.backGroundTaskDescription.setResult(e.getLocalizedMessage());
                                super.backGroundTaskDescription.setStatus(Status.ERROR);
                            }
                            super.backGroundTaskDescription.setEndDate(new Date());
                            File htmlReport = persistenceReportRepository.writeString(
                                    (String) super.backGroundTaskDescription.getResult(),
                                    "report_" + super.backGroundTaskDescription.getUuid() + ".html"
                            );
                            super.backGroundTaskDescription.setResult(htmlReport.getName());
                            persistenceReportRepository.writeEntity(
                                    super.backGroundTaskDescription,
                                    "job_" + super.backGroundTaskDescription.getUuid() + ".json");
                            return backGroundTaskDescription;
                        } catch (Exception e){
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
                addBackGroundTask(uuid, future);
                tasks.add(uuid);
            }
        }
        return tasks;
    }

    public String getReport(Integer startSample, Integer endSample, String jobName){
        String uuid = UUID.randomUUID().toString();
        HashMap params = new HashMap();
        params.put("startSampleId", startSample);
        params.put("endSampleId", endSample);
        params.put("jobName", jobName);
        Future future = executors.submit(new BackgroundTask(
                serversRepository,
                new BackGroundTaskDescription(params, "get_report for all servers")
        ) {

            @Override
            public void run() {

            }

            @Override
            public Object call() throws Exception {
                try {
                    super.backGroundTaskDescription.setName(jobName);
                    super.backGroundTaskDescription.setUuid(uuid);
                    super.backGroundTaskDescription.setStartDate(new Date());
                    super.backGroundTaskDescription.setResult(super.serversRepository.getReport(startSample, endSample));
                    super.backGroundTaskDescription.setEndDate(new Date());
                    super.backGroundTaskDescription.setStatus(Status.SUCCESS);
                    File htmlReport = persistenceReportRepository.writeString(
                            (String) super.backGroundTaskDescription.getResult(),
                            "report_" + super.backGroundTaskDescription.getUuid() + ".html"
                    );
                    super.backGroundTaskDescription.setResult(htmlReport.getName());
                    persistenceReportRepository.writeEntity(
                            super.backGroundTaskDescription,
                            "job_" + super.backGroundTaskDescription.getUuid() + ".json");
                    return backGroundTaskDescription;
                } catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        addBackGroundTask(uuid, future);
        return uuid;
    }

    public String getReport(Integer startSample, Integer endSample, Integer serverId, String jobName){
        String uuid = UUID.randomUUID().toString();
        HashMap params = new HashMap();
        params.put("startSampleId", startSample);
        params.put("endSampleId", endSample);
        params.put("serverId", serverId);
        params.put("jobName", jobName);
        Future future = executors.submit(new BackgroundTask(
                serversRepository,
                new BackGroundTaskDescription(params, "get_report for all servers")
        ) {

            @Override
            public void run() {

            }

            @Override
            public Object call() throws Exception {
                try {
                    super.backGroundTaskDescription.setName(jobName);
                    super.backGroundTaskDescription.setUuid(uuid);
                    super.backGroundTaskDescription.setStartDate(new Date());
                    try {
                        super.backGroundTaskDescription.setResult(super.serversRepository.getReportByServer(serverId, startSample, endSample));
                        super.backGroundTaskDescription.setStatus(Status.SUCCESS);
                    } catch (Exception e){
                        super.backGroundTaskDescription.setResult(e.getLocalizedMessage());
                        super.backGroundTaskDescription.setStatus(Status.ERROR);
                    }

                    super.backGroundTaskDescription.setEndDate(new Date());

                    File htmlReport = persistenceReportRepository.writeString(
                            (String) super.backGroundTaskDescription.getResult(),
                            "report_" + super.backGroundTaskDescription.getUuid() + ".html"
                    );
                    super.backGroundTaskDescription.setResult(htmlReport.getName());
                    persistenceReportRepository.writeEntity(
                            super.backGroundTaskDescription,
                            "job_" + super.backGroundTaskDescription.getUuid() + ".json");
                    return backGroundTaskDescription;
                } catch (Exception e){
                    e.printStackTrace();
                    backGroundTaskDescription.setStatus(Status.ERROR);

                    File htmlReport = persistenceReportRepository.writeString(
                            (String) super.backGroundTaskDescription.getResult(),
                            "report_" + super.backGroundTaskDescription.getUuid() + ".html"
                    );
                    backGroundTaskDescription.setResult(htmlReport.getName());
                    persistenceReportRepository.writeEntity(
                            (String) super.backGroundTaskDescription.getResult(),
                            "job_" + super.backGroundTaskDescription.getUuid() + ".json");
                    return backGroundTaskDescription;
                }
            }
        });
        addBackGroundTask(uuid, future);
        return uuid;
    }

    public String getReport(Integer startSample, Integer endSample, Integer secondStartSample, Integer secondEndSample, Integer serverId, String jobName){
        String uuid = UUID.randomUUID().toString();
        HashMap params = new HashMap();
        params.put("startSampleId", startSample);
        params.put("endSampleId", endSample);
        params.put("secondStartSample", secondStartSample);
        params.put("secondEndSample", secondEndSample);
        Future future = executors.submit(new BackgroundTask(
                serversRepository,
                new BackGroundTaskDescription(params, "get_report for all servers")
        ) {

            @Override
            public void run() {

            }

            @Override
            public Object call() throws Exception {
                try {
                    super.backGroundTaskDescription.setName(jobName);
                    super.backGroundTaskDescription.setUuid(uuid);
                    super.backGroundTaskDescription.setStartDate(new Date());
                    try {
                        super.backGroundTaskDescription.setResult(super.serversRepository.getDiffReport(serverId, startSample, endSample, secondStartSample, secondEndSample));
                        super.backGroundTaskDescription.setStatus(Status.SUCCESS);
                    } catch (Exception e){
                        super.backGroundTaskDescription.setResult(e.getLocalizedMessage());
                        super.backGroundTaskDescription.setStatus(Status.ERROR);
                    }
                    super.backGroundTaskDescription.setEndDate(new Date());
                    File htmlReport = persistenceReportRepository.writeString(
                            (String) super.backGroundTaskDescription.getResult(),
                            "report_" + super.backGroundTaskDescription.getUuid() + ".html"
                    );
                    super.backGroundTaskDescription.setResult(htmlReport.getName());
                    persistenceReportRepository.writeEntity(
                            super.backGroundTaskDescription,
                            "job_" + super.backGroundTaskDescription.getUuid() + ".json");
                    return backGroundTaskDescription;
                } catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        addBackGroundTask(uuid, future);
        return uuid;
    }

    public Future getBackgroundJob(String uuid){
        return backgroundTasks.get(uuid);
    }

    public ConcurrentHashMap<String, BackGroundTaskDescription> getResultsFromStorage(){
        return persistenceReportRepository.getStorage();
    }

    private void addBackGroundTask(String uuid, Future future){
        if (backgroundTasks.size() < MAX_BACKGROUND_SIZE){
            backgroundTasks.put(uuid, future);
        } else {
            Future internalFuture = null;
            String internalUuid = "";
            do {
                internalUuid = backgroundTasks.keys().nextElement();
                internalFuture = backgroundTasks.get(internalUuid);
            } while (!internalFuture.isDone());
            backgroundTasks.remove(internalUuid);
        }
    }
}
