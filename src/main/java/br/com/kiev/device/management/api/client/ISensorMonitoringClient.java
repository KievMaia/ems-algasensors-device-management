package br.com.kiev.device.management.api.client;

import br.com.kiev.device.management.api.model.reponse.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange("/api/sensors/{sensorId}/monitoring")
public interface ISensorMonitoringClient {

    @PutExchange("/enable")
    void enableMonitoring(TSID sensorId);

    @DeleteExchange("/enable")
    void disableMonitoring(TSID sensorId);

    @GetExchange
    SensorMonitoringOutput getDetail(TSID sensorId);
}
