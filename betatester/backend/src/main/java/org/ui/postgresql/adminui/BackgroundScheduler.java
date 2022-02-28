package org.ui.postgresql.adminui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ui.postgresql.adminui.services.BackGroundTaskDescription;
import org.ui.postgresql.adminui.services.PersistenceReportRepository;
import org.ui.postgresql.adminui.services.ServerInstanceToolbox;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BackgroundScheduler {
    private Logger logger = LoggerFactory.getLogger(BackgroundScheduler.class);
    @Autowired
    private ServerInstanceToolbox serverInstanceToolbox;
    @Autowired
    private PersistenceReportRepository persistenceReportRepository;

    @Scheduled(fixedRate = 5000)
    public void refreshStorageData() {
        zombiesProcessing();
        persistenceReportRepository.getStorage();
    }

    private void zombiesProcessing(){
        ConcurrentHashMap<String, BackGroundTaskDescription> persistence = persistenceReportRepository.getStorage();
        Enumeration<String> jobs = persistence.keys();
        if (jobs.hasMoreElements()){
            do {
                String jobId = jobs.nextElement();
                BackGroundTaskDescription backGroundTaskDescription = persistence.get(jobId);
                if (!persistenceReportRepository.checkExistFile((String) backGroundTaskDescription.getResult())){
                    logger.info("remove item: " + jobId);
                    persistenceReportRepository.removeFromStorage(jobId);
                }
            } while (jobs.hasMoreElements());
        }

    }

}
