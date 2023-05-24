package com.letus.dto;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoverEvent {
    private String message;
    private String status;
    private int packageId;
    private Date date;
    private int toCheckPointId;
    private int destinationId;
    private int nextPointId;
}
