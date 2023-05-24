package com.letus.rutasservice.model;
import javax.validation.constraints.NotBlank;

import jakarta.persistence.*;

@Entity
public class Paquete {
    @Id
    private Long id;

    @NotBlank
    private int checkpointNumberLocation;


    public Paquete(Long id, @NotBlank int checkpointNumberLocation) {
        this.id = id;
        this.checkpointNumberLocation = checkpointNumberLocation;
    }

    public Paquete(Long id, @NotBlank int checkpointNumberLocation, Ruta ruta) {
        this.id = id;
        this.checkpointNumberLocation = checkpointNumberLocation;
        this.ruta = ruta;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ruta", nullable=true)
    private Ruta ruta;

    public Paquete() {
    }

    public Paquete orElseThrow(Object object) {
        return null;
    }

    public void setCheckpointNumberLocation(int checkpointNumberLocation) {
        this.checkpointNumberLocation = checkpointNumberLocation;
    }

    public Long getId() {
        return id;
    }

    public int getCheckpointNumberLocation() {
        return checkpointNumberLocation;
    }

    public Ruta getRuta() {
        return ruta;
    }


}
