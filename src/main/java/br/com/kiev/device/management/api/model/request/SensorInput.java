package br.com.kiev.device.management.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SensorInput {
    @NotBlank(message = "O campo nome deve ser preenchido")
    private String name;
    @NotBlank(message = "O campo location deve ser preenchido")
    private String location;
    @NotBlank(message = "O campo ip deve ser preenchido")
    private String ip;
    @NotBlank(message = "O campo protocol deve ser preenchido")
    private String protocol;
    @NotBlank(message = "O campo model deve ser preenchido")
    private String model;
    private Boolean enable;
}
