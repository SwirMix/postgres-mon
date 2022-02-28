package org.ui.postgresql.adminui.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ui.postgresql.adminui.dto.User;
import org.ui.postgresql.adminui.jwt.TokenObject;
import org.ui.postgresql.adminui.jwt.TokenService;
import org.ui.postgresql.adminui.jwt.impl.InvalidTokenCredentials;
import org.ui.postgresql.adminui.jwt.impl.JwtService;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class PersistenceUserRepository implements TokenService<TokenObject> {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ObjectMapper objectMapper = new ObjectMapper();
    private JwtService jwtService;

    public PersistenceUserRepository(File usersFile, String secret) throws FileNotFoundException {
        final Yaml yaml = new Yaml();
        final ArrayList<LinkedHashMap> loadedYaml = yaml.load(new FileReader(usersFile));
        for (LinkedHashMap entity : loadedYaml){
            String email = loadField(entity, "email");
            String password = loadField(entity, "password");
            if (email!=null && password!=null){
                 users.put(email, new User(email, password));
            }
        }
        jwtService = new JwtService(secret);
    }

    public boolean auth(String email, String password){
        User user = getUserByEmail(email);
        if (user!=null){
            if (user.getPassword().equals(DigestUtils.md5Hex(password))) return true;
        }
        return false;
    }

    private String loadField(LinkedHashMap data, String key){
        String value = null;
        if (data.containsKey(key)) {
            value = (String) data.get(key);
            return value;
        } else return null;
    }

    public void setUsers(ConcurrentHashMap users){
        this.users = users;
    }

    public User getUserByEmail(String email){
        if(users.containsKey(email)){
            return users.get(email);
        } else return null;
    }

    public boolean checkExists(String email){
        return users.containsKey(email);
    }

    public void addUser(User user){
        if (!checkExists(user.getEmail())){
            users.put(user.getEmail(), user);
            String debugUser = null;
            try {
                debugUser = objectMapper.writeValueAsString(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            logger.info("Add new user: " + debugUser);
        } else {
            logger.error("User with current email already exists: " + user.getEmail());
        }
    }

    public User getUserByToken(String token){
        TokenObject tokenObject = decryptToken(token);
        return users.get(tokenObject.getEmail());
    }

    @Override
    public TokenObject decryptToken(String token) {
        return jwtService.decryptToken(token);
    }

    @Override
    public String createToken(TokenObject data) {
        return jwtService.createToken(data);
    }

    public String createToken(String email, String password) throws InvalidTokenCredentials {
        if (auth(email, password)){
            TokenObject tokenObject = new TokenObject(email, 1000*3600*24*365);
            return createToken(tokenObject);
        } else throw new InvalidTokenCredentials();
    }
}
