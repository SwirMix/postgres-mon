package org.ui.postgresql.adminui.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ui.postgresql.adminui.dto.InstanceInterval;
import org.ui.postgresql.adminui.dto.Server;
import org.ui.postgresql.adminui.dto.ServerSample;
import org.ui.postgresql.adminui.jwt.TokenObject;
import org.ui.postgresql.adminui.jwt.impl.InvalidTokenCredentials;
import org.ui.postgresql.adminui.services.*;
import org.ui.postgresql.adminui.web.requests.*;
import org.ui.postgresql.adminui.web.responses.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private PersistenceUserRepository userService;
    @Autowired
    private PersistenceReportRepository persistenceReportRepository;
    @Autowired
    private ServersService serversService;
    @Autowired
    private ServerInstanceToolbox serverInstanceToolbox;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/uiadmin/queries")
    public ResponseEntity getServers(
            @RequestParam(required = true) long serverId, @RequestParam(required = true) long sampleId){
        List<QueriesDataResponse> queries = serversService.getQueriesForBySample(serverId, sampleId);
        return new ResponseEntity<>(queries, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/uiadmin/queries/compare")
    public ResponseEntity getQueriesHit(
            @RequestParam(required = true) int firstServerId,
            @RequestParam(required = true) int firstSampleId,
            @RequestParam(required = true) int secondServerId,
            @RequestParam(required = true) int secondSampleId
    ){
        SampleQueries firstServer = serversService.getQueriesBySample(firstServerId, firstSampleId);
        SampleQueries secondServer = serversService.getQueriesBySample(secondServerId, secondSampleId);

        HashMap response = new HashMap();
        response.put("firstServer", firstServer);
        response.put("secondServer", secondServer);
        response.put("except", serversService.getExceptQueries(firstServerId, firstSampleId, secondServerId, secondSampleId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/uiadmin/server")
    public ResponseEntity createNewServerEntity(
            @RequestBody InstanceRequest request,
            @RequestHeader String token
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            TokenObject tokenObject = userService.decryptToken(token);
            serversService.createServerInstance(request);
            return new ResponseEntity<>(new Message("server was created"), HttpStatus.CREATED);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/uiadmin/server/check")
    public ResponseEntity checkServerEntity(
            @RequestBody InstanceRequest request,
            @RequestHeader String token
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            InstanceResponse response = null;
            TokenObject tokenObject = userService.decryptToken(token);
            try {
                response = serverInstanceToolbox.checkInstance(request);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return new ResponseEntity<>(new Message("internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/uiadmin/auth")
    public ResponseEntity auth(@RequestBody AuthRequest authRequest){
        if (authRequest.getEmail()!=null && authRequest.getPassword()!=null){
            try {
                String token = userService.createToken(authRequest.getEmail(), authRequest.getPassword());
                HashMap<String, String> responseBody = new HashMap<>();
                responseBody.put("email", authRequest.getEmail());
                responseBody.put("token", token);
                return new ResponseEntity(responseBody, HttpStatus.OK);
            } catch (InvalidTokenCredentials e) {
                e.printStackTrace();
                return new ResponseEntity(new Message("invalid credentials"), HttpStatus.FORBIDDEN);
            }

        } else return new ResponseEntity(new Message("invalid body"), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/uiadmin/servers")
    public ResponseEntity getServers(
            @RequestHeader String token
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            try {
                TokenObject tokenObject = userService.decryptToken(token);
            } catch (Exception e){
                return new ResponseEntity<>(new Message("invalid token"), HttpStatus.FORBIDDEN);
            }

            List<Server> servers = serversService.getAllServers();
            return new ResponseEntity<>(servers, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/uiadmin/servers/{id}")
    public ResponseEntity getServers(
            @RequestHeader String token,
            @PathVariable Integer id
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            try {
                TokenObject tokenObject = userService.decryptToken(token);
            } catch (Exception e){
                return new ResponseEntity<>(new Message("invalid token"), HttpStatus.FORBIDDEN);
            }

            Server server = serversService.getServerById(id);
            return new ResponseEntity<>(server, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/uiadmin/samples")
    public ResponseEntity getSamples(
            @RequestHeader String token
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            try {
                TokenObject tokenObject = userService.decryptToken(token);
            } catch (Exception e){
                return new ResponseEntity<>(new Message("invalid token"), HttpStatus.FORBIDDEN);
            }
            List<ServerSample> samples = serversService.getAllSamples();
            ArrayList<SampleResponse> response = new ArrayList<>();
            for (ServerSample sample : samples){
                response.add(
                        new SampleResponse(
                            sample.getSamplePK().getSampleId(),
                            sample.getSamplePK().getServerId(),
                            sample.getServer().getServerName(),
                            sample.getServer().getServerCreated(),
                            sample.getSampleTime()
                        )
                );
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/uiadmin/samples")
    public ResponseEntity takeSamples(
            @RequestBody TakeSampleRequest takeSampleRequest,
            @RequestHeader String token
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            try {
                TokenObject tokenObject = userService.decryptToken(token);
            } catch (Exception e){
                return new ResponseEntity<>(new Message("invalid token"), HttpStatus.FORBIDDEN);
            }
            List<String> jobs = serverInstanceToolbox.takeSample(takeSampleRequest);
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/uiadmin/reports/check")
    public ResponseEntity checkReportInterval(@RequestBody CheckReportRequest request){
        ArrayList<CheckReportResponse> series = new ArrayList<>();
        for (List<Integer> interval : request.getSamples()){
            List<Server> serversCol = new ArrayList<>();
            List<ServerSample> firstServerSamples = serversService.getSampleById(interval.get(0));
            List<ServerSample> secondServerSamples = serversService.getSampleById(interval.get(1));
            for (ServerSample firstSample : firstServerSamples){
                for (ServerSample secondSample : secondServerSamples){
                    if (firstSample.getServer().equals(secondSample.getServer())){
                        serversCol.add(firstSample.getServer());
                    }
                }
            }
            ArrayList<InstanceInterval> data = new ArrayList<>();
            CheckReportResponse checkReportResponse = new CheckReportResponse();
            for (Server server : serversCol){
                InstanceInterval serversInterval = new InstanceInterval();
                List<Long> axis = new ArrayList<>();
                axis.add(firstServerSamples.get(0).getSampleTime().getTime());
                axis.add(secondServerSamples.get(0).getSampleTime().getTime());
                serversInterval.setX(server.getServerName());
                serversInterval.setY(axis);
                data.add(serversInterval);
            }
            checkReportResponse.setData(data);
            checkReportResponse.setName("Samples [" +
                    firstServerSamples.get(0).getSamplePK().getSampleId() +
                    "-" +
                    secondServerSamples.get(0).getSamplePK().getSampleId() + "]"
            );
            series.add(checkReportResponse);
        }
        return new ResponseEntity(series, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/uiadmin/reports")
    public ResponseEntity getAllReports(@RequestParam(required = false) String id){
        HashMap response = new HashMap();
        if (id==null){
            Collection data = persistenceReportRepository.getStorage().values();

            response.put("size", data.size());
            response.put("content", data);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            BackGroundTaskDescription taskDescription = persistenceReportRepository.getDescription(id);
            response.put("content", taskDescription);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/uiadmin/reports")
    public ResponseEntity getReports(
            @RequestBody GetReportRequest request,
            @RequestHeader String token
    ){
        if (token==null){
            return new ResponseEntity<>(new Message("no token. Auth pls."), HttpStatus.FORBIDDEN);
        } else {
            try {
                TokenObject tokenObject = userService.decryptToken(token);
            } catch (Exception e){
                return new ResponseEntity<>(new Message("invalid token"), HttpStatus.FORBIDDEN);
            }
            ArrayList response = new ArrayList();
            if (request.getSamples().getSamples().size()==1){
                for (Integer serverId : request.getServers()){
                    Server server = serversService.getServerById(serverId);
                    response.add(serverInstanceToolbox.getReport(
                            request.getSamples().getSamples().get(0).get(0),
                            request.getSamples().getSamples().get(0).get(1),
                            serverId,
                            request.getName() + " [" + server.getServerName() + " ID: " + server.getServerId() + "]"
                    ));
                }
            } else {
                for (Integer serverId : request.getServers()){
                    Server server = serversService.getServerById(serverId);
                    response.add(serverInstanceToolbox.getReport(
                            request.getSamples().getSamples().get(0).get(0),
                            request.getSamples().getSamples().get(0).get(1),
                            request.getSamples().getSamples().get(1).get(0),
                            request.getSamples().getSamples().get(1).get(1),
                            serverId,
                            server.getServerName() + "[ID: " + server.getServerId() + "]"
                    ));
                }
            }
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/profiler/{report_name}")
    public void getFile(@PathVariable("report_name") String fileName, HttpServletResponse response) {
        Path file = Paths.get(persistenceReportRepository.getStorageLocation().getAbsolutePath(), fileName);
        if (Files.exists(file)){
            //response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("text/html");

            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                logger.info("Error writing file to output stream. Filename was '{}'" + fileName, e);
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
    }
}
