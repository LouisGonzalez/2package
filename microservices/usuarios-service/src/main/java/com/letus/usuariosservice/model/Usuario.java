package com.letus.usuariosservice.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
	@Size(max = 50)
	private String name;

    @NotBlank
	@Size(max = 50)
	private String lastName;

    @NotBlank
	@Size(max = 15)
	private String phoneNumber;

    @NotBlank
	@Size(max = 50)
	private String address;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	Set<Paquete> paquetes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	Set<Notificacion> notificacions;

    public Usuario() {
    }

    public Usuario(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String lastName,
            @NotBlank @Size(max = 15) String phoneNumber, @NotBlank @Size(max = 50) String address) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    
}
