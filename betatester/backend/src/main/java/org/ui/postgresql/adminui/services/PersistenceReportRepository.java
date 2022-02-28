package org.ui.postgresql.adminui.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class PersistenceReportRepository {
    private File storage;
    private Logger logger = LoggerFactory.getLogger(PersistenceReportRepository.class);
    private static ConcurrentHashMap<String, BackGroundTaskDescription> reports = new ConcurrentHashMap<>();

    public File getStorageLocation(){
        return storage;
    }

    public void setReports(ConcurrentHashMap<String, BackGroundTaskDescription> reports) {
        this.reports = reports;
    }

    public PersistenceReportRepository(String storagePath){
        this.storage = new File(storagePath);
        if (!storage.exists()){
            storage.mkdir();
        }
    }

    public File[] getFiles(){
        return storage.listFiles();
    }

    public File writeEntity(Object object, String name) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resultFile = storage.toPath().resolve(name).toFile();
        objectMapper.writeValue(resultFile, object);
        return resultFile;
    }

    public File writeString(String value, String name){
        File resultFile = storage.toPath().resolve(name).toFile();
        try(FileWriter writer = new FileWriter(resultFile.getAbsoluteFile(), false))
        {
            writer.write(value);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return resultFile;
    }

    public boolean checkExistFile(String name){
        return storage.toPath().resolve(name).toFile().exists();
    }

    public void removeFromStorage(String uuid){
        BackGroundTaskDescription description = reports.get(uuid);
        reports.remove(uuid);
        storage.toPath().resolve("job_" + description.getUuid() + ".json").toFile().deleteOnExit();
    }

    public BackGroundTaskDescription getDescription(String id){
        return reports.get(id);
    }

    public ConcurrentHashMap<String, BackGroundTaskDescription> getStorage(){
        File[] storeFiles = storage.listFiles();
        ObjectMapper objectMapper = new ObjectMapper();
        for (File file : storeFiles){
            if (file.getName().contains(".json")){
                try {
                    BackGroundTaskDescription description = objectMapper.readValue(file, BackGroundTaskDescription.class);
                    if (!reports.containsKey(description.getUuid())){
                        if (storage.toPath().resolve((String) description.getResult()).toFile().exists()){
                            reports.put(description.getUuid(), description);
                        }

                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                    file.delete();
                    logger.error(e.getMessage());
                }
            }
        }
        return reports;
    }

}
