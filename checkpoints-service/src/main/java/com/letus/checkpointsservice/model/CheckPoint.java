package com.letus.checkpointsservice.model;

import jakarta.validation.constraints.*;

import java.util.Collections;
import java.util.Set;

import jakarta.persistence.*;

@Entity 
public class CheckPoint {
    @Id
    private Long id;

    @NotBlank
	@Size(max = 50)
	private String name;

    @NotBlank
	@Size(max = 150)
	private String address;

    @OneToMany(mappedBy = "checkPoint")
	Set<Paquete> paquetes;

    public CheckPoint(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 150) String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        paquetes = Collections.emptySet();  
    }

    public CheckPoint() {
    }

    
}
