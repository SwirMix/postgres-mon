package org.ui.postgresql.adminui.services;


import org.junit.Test;

import java.io.File;

public class PersistenceReportRepositoryTests {
    private PersistenceReportRepository persistenceReportRepository;
    private String storage = "storage/reports";

    @Test
    public void readRepository(){
        persistenceReportRepository = new PersistenceReportRepository(storage);
        File[] files = persistenceReportRepository.getFiles();
        for (File file : files){
            System.out.println(file.getName());
        }
    }
}
