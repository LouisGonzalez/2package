package com.letus.rutasservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioEvent {
    private String message;
    private String status;
    private Envio envio;
}
