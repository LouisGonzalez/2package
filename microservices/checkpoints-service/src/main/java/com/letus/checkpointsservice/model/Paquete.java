package com.letus.checkpointsservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Paquete {
    @Id
    private Long id;

    private int nexPointId;

    @NotBlank
    // @ManyToOne
    // @JoinColumn(name="destination", nullable=false)
    private int idDestination;

    @OneToMany(mappedBy = "movPaquete")
    Set<Movement> movements;

    @ManyToOne
    @JoinColumn(name="checkPoint", nullable=true)
    private CheckPoint checkPoint;

    public Paquete() {
    }

    public Paquete(Long id, int nexPointId, @NotBlank int idDestination, CheckPoint checkPoint) {
        this.id = id;
        this.nexPointId = nexPointId;
        this.idDestination = idDestination;
        this.checkPoint = checkPoint;
    }

    public int getNexPointId() {
        return nexPointId;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public int getIdDestination() {
        return idDestination;
    }


    
}   
