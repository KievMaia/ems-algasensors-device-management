package br.com.kiev.device.management.api.client.exception;

import java.io.Serial;

public class SensorMonitoringClientBadGatewayException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public SensorMonitoringClientBadGatewayException(String message) {
        super(message);
    }
}
