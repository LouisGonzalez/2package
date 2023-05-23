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

    private float price;

    private int numberOfDays;


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
    

    public Ruta(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String start,
            @NotBlank @Size(max = 50) String finish, float price, int numberOfDays) {
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.price = price;
        this.numberOfDays = numberOfDays;
    }

    public Ruta() {
    }

    public void setCheckpoints(List<CheckPoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public void setPaquetes(Set<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public float getPrice() {
        return price;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public List<CheckPoint> getCheckpoints() {
        return checkpoints;
    }

    public Set<Paquete> getPaquetes() {
        return paquetes;
    }
    public int getLastId(){
        return checkpoints.get(checkpoints.size()-1).getId().intValue();
    }

    public int getNextId(int num){
        if (num==checkpoints.size()-1) {
            return checkpoints.get(num).getId().intValue();
        }else{
            return checkpoints.get(num+1).getId().intValue();
        }
    }
    
    

    
    
}
