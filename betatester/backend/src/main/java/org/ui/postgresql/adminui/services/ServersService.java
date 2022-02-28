package org.ui.postgresql.adminui.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ui.postgresql.adminui.dto.*;
import org.ui.postgresql.adminui.repositories.ServersRepository;
import org.ui.postgresql.adminui.web.requests.InstanceRequest;
import org.ui.postgresql.adminui.web.responses.CompareQueriesResponse;
import org.ui.postgresql.adminui.web.responses.QueriesData;
import org.ui.postgresql.adminui.web.responses.QueriesDataResponse;
import org.ui.postgresql.adminui.web.responses.SampleQueries;

import java.util.*;

@Service
public class ServersService {
    @Autowired
    private ServersRepository serversRepository;
    private static final String CONNECT_STRING_TEMPLATE = "host=%s dbname=%s port=%s user=%s password=%s";

    public Server getServerById(Integer serverId){
        Optional<Server> server = serversRepository.findById(serverId);
        if (server.isPresent()) {
            return server.get();
        } else {
            return null;
        }
    }

    public List<ServerSample> getSampleById(int sampleId){
        List<ServerSample> samples = serversRepository.getSampleById(sampleId);
        return samples;
    }

    public List<ServerSample> getAllSamples(){
        return serversRepository.getAllServerSample();
    }

    public List<Server> getAllServers(){
        List<Server> servers = serversRepository.findAll();
        for (Server server : servers){
            if (server.getServerDescription()==null){
                server.setServerDescription("");
            }

            if (server.getDbExclude()==null){
                String[] exclude = new String[0];
                server.setDbExclude(exclude);
            } else {
                ArrayList<String> exclude = new ArrayList<String>();
                for (String item : server.getDbExclude()){
                    if (item==null){
                        item = "";
                        exclude.add(item);
                    }
                }

                String[] fixExclude = new String[exclude.size()];
                for (int i = 0; i < exclude.size(); i++){
                    fixExclude[i] = exclude.get(i);
                }
                server.setDbExclude(fixExclude);
            }
        }
        return servers;
    }

    public void createServerInstance(InstanceRequest instanceRequest){
        //SELECT profile.create_server('omega','host=name_or_ip dbname=postgres port=5432 user=profile_mon password=pwd_mon');
        //set_server_description('omega', 'description text');
        String connstr = String.format(CONNECT_STRING_TEMPLATE,
                instanceRequest.getHost(),
                instanceRequest.getDatabase(),
                instanceRequest.getPort(),
                instanceRequest.getUser(),
                instanceRequest.getPassword());

        serversRepository.createServerInstance(instanceRequest.getName(), connstr);
        serversRepository.setServerDescription(instanceRequest.getName(), instanceRequest.getDescription());
    }

    public SampleQueries getQueriesBySample(int serverId, int sampleId){
        ServerSample sample = serversRepository.getSampleByServerAndId(serverId, sampleId);
        List<QueriesDataResponse> queries = getQueriesForBySample(serverId, sampleId);
        SampleQueries sampleQueries = new SampleQueries().setQueries(queries).setServerSample(sample);
        return sampleQueries;
    }

    public List<CompareQueriesResponse> getCompareQueries(SampleQueries firstServer, SampleQueries secondServer){
        List<CompareQueriesResponse> data = new ArrayList<>();
        for (QueriesDataResponse query : firstServer.getQueries()){
            for (QueriesDataResponse secondQuery : secondServer.getQueries()){
                if (query.getQueryId()==secondQuery.getQueryId()){

                    CompareQueriesResponse compareItem = new CompareQueriesResponse();
                    compareItem.setFirstServer(firstServer.getServerSample());
                    compareItem.setSecondServer(secondServer.getServerSample());
                    compareItem.setFirstSampleQuery(query);
                    compareItem.setSecondSampleQuery(secondQuery);
                    compareItem.setHit(true);
                    data.add(compareItem);
                }
            }
        }
        return data;
    }

    public List<QueriesDataResponse> getQueriesForBySample(long serverId, long sampleId){
        ArrayList<QueriesDataResponse> result = new ArrayList<>();
        List<SampleStatement> queriesData = serversRepository.getSampleQueries(sampleId, serverId);
        for (SampleStatement data : queriesData){
            StmtList query = serversRepository.getQueryValue(data.getPrimaryKey().getServerId(), data.getQueryidMd5());
            result.add(new QueriesDataResponse()
                    .setQueryidMd5(data.getQueryidMd5())
                    .setQueryId(data.getPrimaryKey().getQueryid())
                    .setQuery(query.getQuery())
                    .setUserId(data.getPrimaryKey().getUserId())
                    .setDatId(data.getPrimaryKey().getDatid())
                    .setCalls(data.getCalls())
                    .setTotal_exec_time(data.getTotalExecTime())
                    .setMin_exec_time(data.getMinExecTime())
                    .setMax_exec_time(data.getMaxExecTime())
                    .setMean_exec_time(data.getMeanExecTime())
                    .setStddev_exec_time(data.getStddevExecTime())
                    .setRows(data.getRows())
                    .setShared_blks_hit(data.getSharedBlksHit())
                    .setShared_blks_read(data.getSharedBlksRead())
                    .setShared_blks_dirtied(data.getSharedBlksDirtied())
                    .setShared_blks_written(data.getSharedBlksWritten())
                    .setLocal_blks_hit(data.getLocalBlksHit())
                    .setLocal_blks_read(data.getLocalBlksRead())
                    .setLocal_blks_dirtied(data.getLocalBlksDirtied())
                    .setLocal_blks_written(data.getLocalBlksWritten())
                    .setTemp_blks_written(data.getTempBlksWritten())
                    .setTemp_blks_dirtied(data.getTempBlksDirtied())
                    .setBlk_read_time(data.getBlkReadTime())
                    .setBlk_write_time(data.getBlkWriteTime())
                    .setWal_records(data.getWalRecords())
                    .setWal_bytes(data.getWalBytes())
                    .setWal_fpi(data.getWalFpi())
            );
        }
        return result;
    }

    public List<QueriesData> getExceptQueries(long firstServerId, long firstSampleId, long secondServerId, long secondSampleId){
        List<QueriesData> queries = new ArrayList<>();
        List<Object[]> data = serversRepository.getExceptQueries(firstServerId, firstSampleId, secondServerId, secondSampleId);
        for (Object[] item : data){
            QueriesData query = new QueriesData(item);
            queries.add(query);
        }
        data = serversRepository.getExceptQueries(secondServerId, secondSampleId, firstServerId, firstSampleId);
        for (Object[] item : data){
            QueriesData query = new QueriesData(item);
            queries.add(query);
        }
        return queries;
    }

}
