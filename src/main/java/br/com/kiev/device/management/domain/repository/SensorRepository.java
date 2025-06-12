package br.com.kiev.device.management.domain.repository;

import br.com.kiev.device.management.domain.model.Sensor;
import br.com.kiev.device.management.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
