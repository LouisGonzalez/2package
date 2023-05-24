package com.letus.usuariosservice.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Paquete {
    @Id
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private EState state;

    @OneToMany(mappedBy = "notiPaquete")
    Set<Notificacion> notificacions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario", nullable=true)
    private Usuario usuario;

    public void setState(EState state) {
        this.state = state;
    }

    public EState getState() {
        return state;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    

}
