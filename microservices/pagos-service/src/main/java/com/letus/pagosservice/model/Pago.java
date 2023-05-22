package com.letus.pagosservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

import java.sql.Date;


import jakarta.persistence.*;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
	private int userId;

    @NotBlank
	private int packageId;

    @NotBlank
    private Float amount;

    @NotBlank
    private Date date;

    public Pago() {
    }

    public Pago(@NotBlank int userId, @NotBlank int packageId, @NotBlank Float amount, @NotBlank Date date) {
        this.userId = userId;
        this.packageId = packageId;
        this.amount = amount;
        this.date = date;
    }
    
}
