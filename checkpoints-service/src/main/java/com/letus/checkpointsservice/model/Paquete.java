package com.letus.checkpointsservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    //private CheckPoint nexPoint;

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

    
}   
