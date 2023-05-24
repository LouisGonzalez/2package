package com.letus.usuariosservice.dto;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecibirDTO {
    private int packageId;
    private Date date;
    private int userId;
}
