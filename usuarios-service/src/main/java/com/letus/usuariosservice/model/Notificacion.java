package com.letus.usuariosservice.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Notificacion {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private Date date;

    @NotBlank
    @ManyToOne
    @JoinColumn(name="notiPaquete", nullable=false)
    private Paquete notiPaquete;

    @ManyToOne
    @JoinColumn(name="usuario", nullable=true)
    private Usuario usuario;

    public Notificacion(@NotBlank Date date, @NotBlank Paquete notiPaquete, Usuario usuario) {
        this.date = date;
        this.notiPaquete = notiPaquete;
        this.usuario = usuario;
    }

    public Notificacion() {
    }

    




}
