package com.letus.rutasservice.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
    import jakarta.validation.constraints.*;
    import jakarta.persistence.*;

@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
	@Size(max = 50)
	private String name;

    @NotBlank
	@Size(max = 50)
	private String start;

    @NotBlank
	@Size(max = 50)
	private String finish;


    @OneToMany(mappedBy = "ruta")
	List<CheckPoint> checkpoints;

    @OneToMany(mappedBy = "ruta")
	Set<Paquete> paquetes;

    public void addPaquete(Paquete paquete){
        
        paquetes.add(paquete);
    }

    public Ruta(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String start,
            @NotBlank @Size(max = 50) String finish, List<CheckPoint> checkpoints, Set<Paquete> paquetes) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.checkpoints = checkpoints;
        this.paquetes = paquetes;
    }

    public Ruta(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String start,
            @NotBlank @Size(max = 50) String finish) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.checkpoints= new ArrayList<CheckPoint>();
        this.paquetes= Collections.<Paquete>emptySet();
            
        
    }

    public Ruta() {
    }

    
    

    
    
}
