package com.letus.rutasservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteColaResponse {
    private int paqueteId;
    private int rutaId;
    private String destination;


    
}
