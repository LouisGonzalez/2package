package com.letus.rutasservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.dto.CheckpointEvent;
import com.letus.dto.MoverEvent;
import com.letus.rutasservice.dto.MoverDTO;
import com.letus.rutasservice.dto.RutaDTO;
import com.letus.rutasservice.kafka.CheckpointProducer;
import com.letus.rutasservice.kafka.MoverIniProducer;
import com.letus.rutasservice.model.CheckPoint;
import com.letus.rutasservice.service.RutaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    RutaService rutaService;

    private CheckpointProducer checkpointProducer;

    private MoverIniProducer moverIniProducer;

    @PostMapping("/create_test_ruta")
    public String testRuta(){

        rutaService.createRutaTest();

        return "Created";
    }

    

    public RutaController(CheckpointProducer checkpointProducer, MoverIniProducer moverIniProducer) {
        this.checkpointProducer=checkpointProducer;
        this.moverIniProducer = moverIniProducer;
    }
    

    




    @GetMapping("/get-rutas")
    public List<Object[]> getRutas() {
        return rutaService.getRutas();
    }

    @PostMapping("/createRuta")
    public String placeOrder(@RequestBody RutaDTO ruta){


        CheckpointEvent checkpointEvent = new CheckpointEvent();
        List<CheckPoint> oldChecks = rutaService.createRuta(ruta);
        checkpointEvent.setStatus("PENDING");
        checkpointEvent.setMessage("order status is in pending state");
        checkpointEvent.setCheckpoints(rutaService.createList(oldChecks, ruta));
        //checkpointEvent.setCheckpoints(new Checkpoint(1,"name","add"));
        checkpointProducer.sendMessage(checkpointEvent);

        return "Order placed successfully ...";
    }
    @PostMapping("/mover-paquete")
    public String moverPaqueteInicio (@RequestBody MoverDTO mover){
       MoverEvent moverEvent = rutaService.moverPaquete(mover);
       moverIniProducer.sendMessage(moverEvent);
        return "Moved successfully ...";
    }

    

    // @PostMapping("/move_to_checkpoint")
    // public String moveToCheckpoint(){
        
    // }
}
