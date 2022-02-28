package org.ui.postgresql.adminui.services;

import org.ui.postgresql.adminui.web.requests.InstanceRequest;
import org.ui.postgresql.adminui.web.requests.TakeSampleRequest;
import org.ui.postgresql.adminui.web.responses.InstanceResponse;

import java.sql.SQLException;
import java.util.List;

public interface ToolboxInterface {
    public InstanceResponse checkInstance(InstanceRequest request) throws ClassNotFoundException, SQLException;

    public List<String> takeSample(TakeSampleRequest takeSampleRequest);
}
