package br.com.kiev.device.management.api.client;

import io.hypersistence.tsid.TSID;

public interface ISensorMonitoringClient {
    void enableMonitoring(TSID sensorId);
    void disableMonitoring(TSID sensorId);
}
