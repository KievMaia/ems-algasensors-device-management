package br.com.kiev.device.management.domain.service;

import br.com.kiev.device.management.api.client.ISensorMonitoringClient;
import br.com.kiev.device.management.api.model.reponse.SensorDetailOutput;
import br.com.kiev.device.management.api.model.reponse.SensorOutput;
import br.com.kiev.device.management.api.model.request.SensorInput;
import br.com.kiev.device.management.domain.mapper.SensorMapper;
import br.com.kiev.device.management.domain.model.Sensor;
import br.com.kiev.device.management.domain.model.SensorId;
import br.com.kiev.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.kiev.device.management.domain.mapper.SensorMapper.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository repository;
    private final ISensorMonitoringClient monitoringClient;

    public SensorOutput create(SensorInput sensorInput) {
        var sensor = toEntity(sensorInput);
        var sensorSaved = repository.save(sensor);
        return toSensorOutput(sensorSaved);
    }

    public SensorOutput update(TSID tsid, SensorInput sensorInput) {
        var sensor = this.getOne(tsid);
        BeanUtils.copyProperties(sensorInput, sensor, "id", "enable");
        var sensorUpdated = repository.save(sensor);
        return toSensorOutput(sensorUpdated);
    }

    public void enable(TSID sensorId) {
        var sensor = this.getOne(sensorId);
        sensor.enable();
        repository.save(sensor);
        monitoringClient.enableMonitoring(sensorId);
    }

    public void disable(TSID sensorId) {
        var sensor = this.getOne(sensorId);
        sensor.disable();
        repository.save(sensor);
        monitoringClient.disableMonitoring(sensorId);
    }

    public Page<SensorOutput> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(SensorMapper::toSensorOutput);
    }

    public SensorOutput getOneById(TSID sensorId) {
        var sensor = this.getOne(sensorId);
        return toSensorOutput(sensor);
    }

    public SensorDetailOutput getOneWithDetail(TSID sensorId) {
        var sensor = this.getOneById(sensorId);
        var sensorMonitoringOutput = monitoringClient.getDetail(sensorId);
        return toSensorDetailOutput(sensor, sensorMonitoringOutput);
    }

    public void delete(TSID sensorId) {
        var sensor = this.getOne(sensorId);
        repository.delete(sensor);
        monitoringClient.disableMonitoring(sensorId);
    }

    public Sensor getOne(TSID sensorId) {
        return repository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Sensor not found"));
    }
}
