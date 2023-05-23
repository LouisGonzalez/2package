package com.letus.rutasservice.dto;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoverDTO {
    private int packageId;
    private Date date;
    
}
