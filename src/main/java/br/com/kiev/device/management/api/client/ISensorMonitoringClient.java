package br.com.kiev.device.management.api.client;

import br.com.kiev.device.management.api.model.reponse.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;

public interface ISensorMonitoringClient {
    void enableMonitoring(TSID sensorId);
    void disableMonitoring(TSID sensorId);
    SensorMonitoringOutput getDetail(TSID sensorId);
}
