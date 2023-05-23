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
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn(name="movCheckPoint", nullable=false)
    private CheckPoint movCheckPoint;

    @NotBlank
    @ManyToOne
    @JoinColumn(name="movPaquete", nullable=false)
    private Paquete movPaquete;



    @NotBlank
    private Date date;

    @NotBlank
    private boolean sending;
    


}
