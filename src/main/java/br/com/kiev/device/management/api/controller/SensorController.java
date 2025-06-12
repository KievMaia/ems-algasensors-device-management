package br.com.kiev.device.management.api.controller;

import br.com.kiev.device.management.api.model.reponse.SensorOutput;
import br.com.kiev.device.management.api.model.request.SensorInput;
import br.com.kiev.device.management.common.IdGenerator;
import br.com.kiev.device.management.domain.model.Sensor;
import br.com.kiev.device.management.domain.model.SensorId;
import br.com.kiev.device.management.domain.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository repository;

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
