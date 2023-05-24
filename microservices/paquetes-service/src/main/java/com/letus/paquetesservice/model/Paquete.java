package com.letus.paquetesservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paquetes")
public class Paquete {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    
    private int userId;
    

    private int rutaId;
    
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Paquete(Long id, int userId, int rutaId, String status) {
        this.id = id;
        this.userId = userId;
        this.rutaId = rutaId;
        this.status = status;
    }
    public Paquete(int userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public Paquete() {
    }

    
}
