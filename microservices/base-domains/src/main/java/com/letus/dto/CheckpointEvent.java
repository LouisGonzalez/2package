package com.letus.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckpointEvent {
    private String message;
    private String status;
    private List<Checkpoint> checkpoints;
}
