package org.ui.postgresql.adminui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.ui.postgresql.adminui.services.PersistenceReportRepository;
import org.ui.postgresql.adminui.services.PersistenceUserRepository;
import java.io.File;
import java.io.FileNotFoundException;

@Component
@Configuration
@EnableAutoConfiguration
public class ApplicationConfiguration{
    @Value("${adminui.secret}")
    private String jwtSecret;
    @Value("${adminui.storage.users}")
    private String users;
    @Value("${adminui.storage}")
    private String storagePath;

    @Bean
    public PersistenceUserRepository initUserRepository() throws FileNotFoundException {
        PersistenceUserRepository persistenceUserRepository = new PersistenceUserRepository(new File(users), jwtSecret);
        return persistenceUserRepository;
    }

    @Bean
    public PersistenceReportRepository initReportRepository(){
        PersistenceReportRepository persistenceReportRepository = new PersistenceReportRepository(storagePath);
        return persistenceReportRepository;
    }

}
