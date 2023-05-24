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

    public Movement(@NotBlank EStatus status, CheckPoint movCheckPoint, @NotBlank Paquete movPaquete,
            @NotBlank Date date, @NotBlank boolean sending) {
        this.status = status;
        this.movCheckPoint = movCheckPoint;
        this.movPaquete = movPaquete;
        this.date = date;
        this.sending = sending;
    }

    public Movement() {
    }
    


}
