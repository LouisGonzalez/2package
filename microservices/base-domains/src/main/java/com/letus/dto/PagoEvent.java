package com.letus.dto;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoEvent {
    private String message;
    private String status;
    private Pago pago;

    
}
