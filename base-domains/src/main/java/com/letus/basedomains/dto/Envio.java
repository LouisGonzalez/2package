package com.letus.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
   private String id;
   private int packageId;
   private int rutaId;

}
