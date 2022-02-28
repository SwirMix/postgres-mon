package org.ui.postgresql.adminui;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ui.postgresql.adminui.jwt.impl.InvalidTokenCredentials;
import org.ui.postgresql.adminui.services.PersistenceUserRepository;
import org.ui.postgresql.adminui.services.ServerInstanceToolbox;
import org.ui.postgresql.adminui.web.requests.InstanceRequest;
import org.ui.postgresql.adminui.web.responses.InstanceResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class PersistenceRepositoryTest {
    private File users = new File("storage/users-credentials.yaml");
    private String email = "ponchick@gmail.com";
    private String validPass = "1234567";
    private String invalidPass = "12345678";
    private String secret = "1234567890";
    private PersistenceUserRepository persistenceUserRepository;

    @Test
    public void userRepositoryValidTest() throws FileNotFoundException {
        persistenceUserRepository = new PersistenceUserRepository(users, secret);
        try {
            String token = persistenceUserRepository.createToken(email, validPass);
            System.out.println(token);
        } catch (InvalidTokenCredentials e) {
            e.printStackTrace();
        }

        System.out.println("fin");
    }

    @Test
    public void serverInstanceToolbox() throws ClassNotFoundException, SQLException {
        ServerInstanceToolbox serverInstanceToolbox = new ServerInstanceToolbox();
        InstanceRequest request = new InstanceRequest();
        request.setHost("localhost");
        request.setPort("5432");
        request.setDatabase("perfcona");
        request.setUser("perfcona");
        request.setPassword("perfcona");
        InstanceResponse response = serverInstanceToolbox.checkInstance(request);

    }
}
