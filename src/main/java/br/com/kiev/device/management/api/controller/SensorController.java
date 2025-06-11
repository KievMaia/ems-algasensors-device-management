package br.com.kiev.device.management.api.controller;

import br.com.kiev.device.management.api.model.request.SensorInput;
import br.com.kiev.device.management.common.IdGenerator;
import br.com.kiev.device.management.domain.model.Sensor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @PostMapping
    @ResponseStatus(CREATED)
    public Sensor create(@RequestBody SensorInput input) {
        return Sensor.builder()
                .id(IdGenerator.generateTSID())
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enable(Boolean.FALSE)
                .build();
    }
}
