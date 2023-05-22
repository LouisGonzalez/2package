package com.letus.pagosservice.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  PagoDTO {
    private int userId;
    private int packageId;
    private Float amount;
    private Date date;

    private Long cardNumber;
    private int cvv;
    private String cardName;
    private String expDate;

    
}
