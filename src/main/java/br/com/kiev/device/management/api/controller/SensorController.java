package br.com.kiev.device.management.api.controller;

import br.com.kiev.device.management.api.model.reponse.SensorOutput;
import br.com.kiev.device.management.api.model.request.SensorInput;
import br.com.kiev.device.management.common.IdGenerator;
import br.com.kiev.device.management.domain.model.Sensor;
import br.com.kiev.device.management.domain.model.SensorId;
import br.com.kiev.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository repository;
    private final SensorRepository sensorRepository;

    @GetMapping("")
    public Page<SensorOutput> search(@PageableDefault(direction = ASC) Pageable pageable) {
        return repository.findAll(pageable).map(this::convertToModel);
    }

    @GetMapping("{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Sensor not found"));
        return this.convertToModel(sensor);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SensorOutput create(@RequestBody SensorInput input) {
        var sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enable(Boolean.FALSE)
                .build();

        sensor = repository.saveAndFlush(sensor);

        return this.convertToModel(sensor);
    }

    @PutMapping("/{sensorId}")
    public SensorOutput update(@PathVariable TSID sensorId, @RequestBody @Validated SensorInput input) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Sensor not found"));

        BeanUtils.copyProperties(input, sensor, "id", "enable");

        var sensorAtualizado = sensorRepository.save(sensor);

        return this.convertToModel(sensorAtualizado);
    }

    @PutMapping("/{sensorId}/enable")
    @ResponseStatus(NO_CONTENT)
    public void enable(@PathVariable @NotNull TSID sensorId) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Sensor not found"));
        sensor.enable();
        sensorRepository.save(sensor);
    }

    @DeleteMapping("/{sensorId}/enable")
    @ResponseStatus(NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Sensor not found"));
        sensor.disable();
        sensorRepository.save(sensor);
    }

    @DeleteMapping("/{sensorId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Sensor not found"));
        sensorRepository.deleteById(new SensorId(sensor.getId().getValue()));
    }

    private SensorOutput convertToModel(Sensor sensor) {
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
}
