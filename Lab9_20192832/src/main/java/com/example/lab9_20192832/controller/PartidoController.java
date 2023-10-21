package com.example.lab9_20192832.controller;

import com.example.lab9_20192832.entity.Deporte;
import com.example.lab9_20192832.entity.Partido;
import com.example.lab9_20192832.repository.HistorialPartidoRepository;
import com.example.lab9_20192832.repository.ParticipanteRepository;
import com.example.lab9_20192832.repository.PartidoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/partido")
public class PartidoController {
    final PartidoRepository partidoRepository;
    final HistorialPartidoRepository historialPartidoRepository;

    public PartidoController(PartidoRepository partidoRepository,
                             HistorialPartidoRepository historialPartidoRepository){
        this.partidoRepository=partidoRepository;
        this.historialPartidoRepository=historialPartidoRepository;
    }

    @PostMapping(value = "registro")
    public ResponseEntity<HashMap<String, Object>> guardarPartido(
            @RequestBody Partido partido,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        partidoRepository.save(partido);

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

        String fechaFormateada = formato.format(fechaActual);

        try {
            Date fechaComoDate = formato.parse(fechaFormateada);
            historialPartidoRepository.crearHistorial(partido.getIdPartido(),1,fechaComoDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (fetchId) {
            responseJson.put("id", partido.getIdPartido());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException4(HttpServletRequest request) {
        HashMap<String, String> responseMap4 = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap4.put("estado", "error");
            responseMap4.put("msg", "Debe enviar un partido");
        }
        return ResponseEntity.badRequest().body(responseMap4);
    }
}
