package org.ui.postgresql.adminui.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.ui.postgresql.adminui.AdminUiApplication;
import org.ui.postgresql.adminui.dto.Server;
import org.ui.postgresql.adminui.dto.ServerSample;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = AdminUiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class ServersServiceTests {
    @Autowired
    private ServersService serversService;

    @Test
    public void getServersTest(){
        List<Server> serverList = serversService.getAllServers();
        boolean result = false;
        for (Server server : serverList){
            if (server.getServerName().equals("local")) result = true;
        }
        Assertions.assertEquals(result, true);
    }

    @Test
    public void checkSamples(){
        List<ServerSample> serverList = serversService.getSampleById(2);
        boolean result = false;
        for (ServerSample server : serverList){
            if (server.getServer().getServerName().equals("local")) result = true;
        }
        Assertions.assertEquals(result, true);
    }
}
