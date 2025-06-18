package br.com.kiev.device.management.api.model.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorDetailOutput {
    private SensorOutput sensor;
    private SensorMonitoringOutput monitoring;
}
