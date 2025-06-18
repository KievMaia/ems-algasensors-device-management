package br.com.kiev.device.management.api.client.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@ResponseStatus(BAD_GATEWAY)
public class SensorMonitoringClientBadGatewayException extends RuntimeException {
}
