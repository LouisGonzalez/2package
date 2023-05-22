package com.letus.checkpointsservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.checkpointsservice.service.CheckpointService;

@RestController
@RequestMapping("/checkpoint")
public class CheckpointController {
    @Autowired
    CheckpointService checkpointService ;

    @GetMapping("/get-paquetes")
    public List<Object[]> getPaquetes() {
        return checkpointService.getCheckpoints();
    }
}
