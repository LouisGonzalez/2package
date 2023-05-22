package com.letus.rutasservice.model;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class CheckPoint {
    @Id
    private Long id;

    @NotBlank
    private int numberInRoute;

    @NotBlank
	@Size(max = 50)
	private String name;

    @ManyToOne
    @JoinColumn(name="ruta", nullable=false)
    private Ruta ruta;

    public CheckPoint() {
    }

    
}
