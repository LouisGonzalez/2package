package com.letus.rutasservice.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutaDTO {
    private String name;
    private String start;
    private String finish;
    private float price;
    private int numberOfDays;
    private List<CheckPointDTO> checkpoints;

}
