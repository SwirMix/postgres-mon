package org.ui.postgresql.adminui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ui.postgresql.adminui.dto.*;
import org.ui.postgresql.adminui.web.responses.QueriesData;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface ServersRepository extends JpaRepository<Server, Integer> {
    @Query("select s from sample s order by s.samplePK.serverId desc")
    List<Sample> findAllSamples();

    @Query("select s from ServerSample s where s.samplePK.sampleId=?1 order by s.samplePK.serverId desc")
    List<ServerSample> getSampleById(int sampleId);

    @Query("select s from ServerSample s order by s.samplePK.serverId desc")
    List<ServerSample> getAllServerSample();

    @Query(value = "select profile.take_sample(?1, true)", nativeQuery = true)
    String takeSampleByServerId(int serverId);

    @Query(value = "select profile.take_sample()", nativeQuery = true)
    Object takeSampleForAll();

    @Query(value = "select profile.get_report(?1,?2)", nativeQuery = true)
    Object getReport(Integer startSample, Integer endSample);

    @Query(value = "select profile.get_diffreport(?1,?2,?3,?4,?5,'',false)", nativeQuery = true)
    Object getDiffReport(Integer serverId, Integer startSample, Integer endSample, Integer startSecondSample, Integer endSecondSample);

    @Query(value = "select profile.get_report(?1,?2,?3)", nativeQuery = true)
    Object getReportByServer(int serverId, Integer startSample, Integer endSample);

    @Query(value = "SELECT profile.create_server(?1,?2)", nativeQuery = true)
    Object createServerInstance(String name, String connstr);

    @Query(value = "SELECT profile.set_server_description(?1, ?2)", nativeQuery = true)
    Object setServerDescription(String server, String desc);

    @Query("select q from SampleStatement q where q.primaryKey.sampleId=?1 and q.primaryKey.serverId=?2")
    List<SampleStatement> getSampleQueries(long sampleId, long serverId);

    @Query("select info from smtp info where info.stmtPK.serverId=?1 and info.stmtPK.queryidMd5=?2")
    StmtList getQueryValue(long serverId, String queryId);

    @Query("select s from ServerSample s where s.server.serverId=?1 and s.samplePK.sampleId=?2")
    ServerSample getSampleByServerAndId(int serverId, int sampleId);

    @Query(value = "select " +
            "ss.server_id, " +
            "ss.sample_id, " +
            "ss.userid, " +
            "ss.datid, " +
            "ss.queryid, " +
            "ss.queryid_md5, " +
            "sl.query, " +
            "ss.plans, " +
            "ss.total_plan_time, " +
            "ss.min_plan_time, " +
            "ss.max_plan_time, " +
            "ss.mean_plan_time, " +
            "ss.stddev_plan_time, " +
            "ss.calls, " +
            "ss.total_exec_time, " +
            "ss.min_exec_time, " +
            "ss.max_exec_time, " +
            "ss.mean_exec_time, " +
            "ss.rows, " +
            "ss.shared_blks_read, " +
            "ss.shared_blks_dirtied, " +
            "ss.shared_blks_written, " +
            "ss.local_blks_hit, " +
            "ss.local_blks_read, " +
            "ss.local_blks_dirtied, " +
            "ss.local_blks_written, " +
            "ss.temp_blks_read," +
            "ss.temp_blks_written," +
            "ss.blk_read_time," +
            "ss.blk_write_time," +
            "ss.wal_records," +
            "ss.wal_fpi," +
            "ss.wal_bytes, ss.shared_blks_hit, ss.stddev_exec_time " +
            "from profile.sample_statements ss join profile.stmt_list sl on ss.queryid_md5 = sl.queryid_md5 " +
            "where ss.queryid in (" +
            "select st.queryid from profile.sample_statements st join profile.stmt_list sl on st.queryid_md5 = sl.queryid_md5 where st.server_id = ?1 and st.sample_id = ?2 " +
            "except " +
            "select st.queryid from profile.sample_statements st join profile.stmt_list sl on st.queryid_md5 = sl.queryid_md5 where st.server_id = ?3 and st.sample_id = ?4 " +
            ") and ss.server_id = ?1 and sl.server_id = ?1 and ss.sample_id = ?2"
    , nativeQuery = true)
    List<Object[]> getExceptQueries(long firstServerId, long firstSampleId, long secondServerId, long secondSampleId);

}
