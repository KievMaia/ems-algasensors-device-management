package br.com.kiev.device.management.api.controller;

import br.com.kiev.device.management.api.model.reponse.SensorOutput;
import br.com.kiev.device.management.api.model.request.SensorInput;
import br.com.kiev.device.management.domain.service.SensorService;
import io.hypersistence.tsid.TSID;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService service;

    @GetMapping("")
    public Page<SensorOutput> search(@PageableDefault(direction = ASC) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId) {
        return service.getOneById(sensorId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SensorOutput create(@RequestBody @Validated SensorInput input) {
        return service.create(input);
    }

    @PutMapping("/{sensorId}")
    public SensorOutput update(@PathVariable TSID sensorId, @RequestBody @Validated SensorInput input) {
        return service.update(sensorId, input);
    }

    @PutMapping("/{sensorId}/enable")
    @ResponseStatus(NO_CONTENT)
    public void enable(@PathVariable @NotNull TSID sensorId) {
        service.enable(sensorId);
    }

    @DeleteMapping("/{sensorId}/enable")
    @ResponseStatus(NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        service.disable(sensorId);
    }

    @DeleteMapping("/{sensorId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        service.delete(sensorId);
    }
}
