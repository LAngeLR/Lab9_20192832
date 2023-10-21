package com.example.lab9_20192832.controller;

import com.example.lab9_20192832.entity.Equipo;
import com.example.lab9_20192832.entity.Participante;
import com.example.lab9_20192832.entity.Participantepartido;
import com.example.lab9_20192832.entity.Partido;
import com.example.lab9_20192832.repository.HistorialPartidoRepository;
import com.example.lab9_20192832.repository.ParticipantePartidoRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partido")
public class PartidoController {
    final PartidoRepository partidoRepository;
    final HistorialPartidoRepository historialPartidoRepository;
    final ParticipantePartidoRepository participantePartidoRepository;
    final ParticipanteRepository participanteRepository;

    public PartidoController(PartidoRepository partidoRepository,
                             HistorialPartidoRepository historialPartidoRepository,
                             ParticipantePartidoRepository participantePartidoRepository,
                             ParticipanteRepository participanteRepository){
        this.partidoRepository=partidoRepository;
        this.historialPartidoRepository=historialPartidoRepository;
        this.participantePartidoRepository=participantePartidoRepository;
        this.participanteRepository=participanteRepository;
    }

    @PostMapping(value = "registro")
    public ResponseEntity<HashMap<String, Object>> guardarPartido(
            @RequestBody Partido partido,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        partidoRepository.save(partido);

        List<Participante> PequipoA = participanteRepository.listaParticipantesEquipo(partido.getEquipoA().getIdEquipo());
        List<Participante> PequipoB = participanteRepository.listaParticipantesEquipo(partido.getEquipoB().getIdEquipo());

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

        String fechaFormateada = formato.format(fechaActual);

        participantePartidoRepository.contadortabla();

        try {
            Date fechaComoDate = formato.parse(fechaFormateada);

            historialPartidoRepository.crearHistorial(partido.getIdPartido(),1,fechaComoDate);

            for (Participante pA : PequipoA) {

                participantePartidoRepository.crearPartixPartido(partido.getIdPartido(),pA.getIdParticipante(),fechaComoDate);
            }

            for (Participante pB : PequipoB) {

                participantePartidoRepository.crearPartixPartido(partido.getIdPartido(),pB.getIdParticipante(),fechaComoDate);
            }
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
