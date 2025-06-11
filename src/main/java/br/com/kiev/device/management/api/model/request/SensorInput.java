package br.com.kiev.device.management.api.model.request;

import lombok.Data;

@Data
public class SensorInput {
    private String name;
    private String location;
    private String ip;
    private String protocol;
    private String model;
    private Boolean enable;
}
