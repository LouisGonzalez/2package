package com.letus.checkpointsservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;

@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    //private CheckPoint nexPoint;

    @NotBlank
    private CheckPoint destination;

    @ManyToOne
    @JoinColumn(name="checkPoint", nullable=true)
    private CheckPoint checkPoint;

    public Paquete() {
    }

    
}   
