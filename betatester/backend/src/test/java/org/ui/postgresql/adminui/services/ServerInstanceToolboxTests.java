package org.ui.postgresql.adminui.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.ui.postgresql.adminui.AdminUiApplication;
import org.ui.postgresql.adminui.repositories.ServersRepository;
import org.ui.postgresql.adminui.web.requests.TakeSampleRequest;
import org.ui.postgresql.adminui.web.responses.QueriesData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = AdminUiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class ServerInstanceToolboxTests {
    @Autowired
    ServerInstanceToolbox serverInstanceToolbox;
    @Autowired
    private ServersRepository serversRepository;

    @Test
    public void getSamplesTest() throws Exception {
        List<Integer> servers = new ArrayList<>();
        servers.add(1);
        TakeSampleRequest takeSampleRequest = new TakeSampleRequest();
        takeSampleRequest.setServers(servers);

        List<String> jobs = serverInstanceToolbox.takeSample(takeSampleRequest);
        for (String job : jobs){
            Future future = serverInstanceToolbox.getBackgroundJob(job);
            BackGroundTaskDescription result = (BackGroundTaskDescription) future.get();
            System.out.println(result);
        }
    }

    @Test
    public void getReportTest() throws Exception {
        List<Integer> servers = new ArrayList<>();
        servers.add(1);
        TakeSampleRequest takeSampleRequest = new TakeSampleRequest();
        takeSampleRequest.setServers(servers);

        List<String> jobs = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            jobs.add(serverInstanceToolbox.getReport(1,6, "test"));
        }

        for (String uuid : jobs){
            Future future = serverInstanceToolbox.getBackgroundJob(uuid);
            while (!future.isDone()){

            }
            BackGroundTaskDescription result = (BackGroundTaskDescription) future.get();
            System.out.println(result.getResult());
        }

    }

    @Test
    public void getQueriesExcept(){
        List<Object[]> data = serversRepository.getExceptQueries(2, 10, 1, 20);
        for (Object[] item : data){
            QueriesData query = new QueriesData(item);
            System.out.println("fin-01");
        }
        System.out.println("fin");
    }
}
