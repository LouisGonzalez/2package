package com.letus.rutasservice.model;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class CheckPoint {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private int numberInRoute;

    @NotBlank
	@Size(max = 50)
	private String name;

    @ManyToOne
    @JoinColumn(name="ruta", nullable=true)
    private Ruta ruta;

    public CheckPoint() {
    }

    public CheckPoint(@NotBlank int numberInRoute, @NotBlank @Size(max = 50) String name, Ruta ruta) {
        this.numberInRoute = numberInRoute;
        this.name = name;
        this.ruta = ruta;
    }

    public Long getId() {
        return id;
    }

    public int getNumberInRoute() {
        return numberInRoute;
    }

    public String getName() {
        return name;
    }

    public Ruta getRuta() {
        return ruta;
    }



    
}
