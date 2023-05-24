package com.letus.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecSiguienteEvent {
    private String message;
    private String status;
    private int packageId;
    private int checkPointId;
    
}
