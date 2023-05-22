package com.letus.checkpointsservice.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    @ManyToOne
    private EStatus status;

    @NotBlank
    private Paquete paquete;

    @NotBlank
    private CheckPoint checkPoint;

    @NotBlank
    private Date date;

    @NotBlank
    private boolean sending;
    


}
