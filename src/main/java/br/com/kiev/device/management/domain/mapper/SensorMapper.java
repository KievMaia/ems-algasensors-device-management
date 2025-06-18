package br.com.kiev.device.management.domain.mapper;

import br.com.kiev.device.management.api.model.reponse.SensorDetailOutput;
import br.com.kiev.device.management.api.model.reponse.SensorMonitoringOutput;
import br.com.kiev.device.management.api.model.reponse.SensorOutput;
import br.com.kiev.device.management.api.model.request.SensorInput;
import br.com.kiev.device.management.common.IdGenerator;
import br.com.kiev.device.management.domain.model.Sensor;
import br.com.kiev.device.management.domain.model.SensorId;

public class SensorMapper {
    private SensorMapper() {}

    public static SensorOutput toSensorOutput(Sensor sensor) {
        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enable(sensor.getEnable())
                .build();
    }

    public static Sensor toEntity(SensorInput input){
        return Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enable(Boolean.FALSE)
                .build();
    }

    public static SensorDetailOutput toSensorDetailOutput(SensorOutput sensorOutput,
                                                          SensorMonitoringOutput monitoringOutput) {
        return SensorDetailOutput.builder()
                .sensor(sensorOutput)
                .monitoring(monitoringOutput)
                .build();
    }
}
