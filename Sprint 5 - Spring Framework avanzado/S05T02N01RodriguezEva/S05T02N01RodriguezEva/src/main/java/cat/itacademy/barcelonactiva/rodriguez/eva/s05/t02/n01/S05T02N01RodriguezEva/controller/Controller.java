package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.controller;

import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Jugador;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Tirada;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.service.ServiceJugador;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.service.ServiceTirada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class Controller {

    @Autowired
    ServiceJugador serviceJugador;

    @Autowired
    ServiceTirada serviceTirada;


    @PostMapping("/agregar")
    public ResponseEntity agregarJugador(@RequestBody Jugador jugador) {
        String mensaje = serviceJugador.verifyUserData(jugador);
        if(mensaje.equals("Creado")) {
            serviceJugador.agregarJugador(jugador);
            return (ResponseEntity.status(HttpStatus.OK))
                    .body(jugador);
        }else {
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                    .body(mensaje);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity <String> actualizarJugador(@RequestBody Jugador jugadorEncontrado, @PathVariable Long id_jugador) {
        Jugador jugador = serviceJugador.findById(id_jugador);
        if(jugador != null) {
            String nombreNuevo = jugadorEncontrado.getNombre();
            jugador.setNombre(nombreNuevo);
            serviceJugador.saveUser(jugador);
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("Actualizado");
        }else {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("No existe el usuario");
        }
    }

    //POST /players/{id}/games/ : un jugador específic realitza una tirada  dels daus.
    @PutMapping ("/play/{id}")
    public ResponseEntity<String> tiraLosDados(@PathVariable Long id) {
        Optional<Jugador> nuevoJugador = Optional.ofNullable(serviceJugador.findById(id));
        if (nuevoJugador.isPresent()) {
            return ResponseEntity.ok(serviceTirada.jugarLosDados(id));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    //DELETE /players/{id}/games: elimina les tirades del jugador
    @DeleteMapping ("/players/{id}/games")
    public ResponseEntity<String> eliminaTiradas(@PathVariable Long id) {
        Optional<Jugador> nuevoJugador = Optional.ofNullable(serviceJugador.findById(id));
        if (nuevoJugador.isPresent()) {
            return ResponseEntity.ok(serviceTirada.borrarTiradas(id));
        }	else {
            return ResponseEntity.badRequest().build();
        }

    }

    //GET /players/: retorna el llistat de tots  els jugadors del sistema  amb el seu  percentatge mig  d’èxits
    @GetMapping ("/players")
    public ResponseEntity<List<Jugador>> getJugadores(List<Jugador> jugadores) {
        return ResponseEntity.ok(serviceJugador.listarJugadoresConExito());
    }

    // GET /players/{id}/games: retorna el llistat de jugades per un jugador.
    @GetMapping ("/players/{id}/games")
    public ResponseEntity<List<Tirada>> listarTiradas(@PathVariable Long id) {
        return ResponseEntity.ok(serviceJugador.getTiradas(id));
    }

    //GET /players/ranking
    @GetMapping ("/players/ranking")
    public ResponseEntity<String> getRanking() {
        return ResponseEntity.ok(serviceJugador.getRankingExito());
    }

    //GET /players/ranking/loser: retorna el jugador  amb pitjor percentatge d’èxit
    @GetMapping ("/players/ranking/loser")
    public ResponseEntity<String> getLoser() {
        return ResponseEntity.ok(serviceJugador.getMenor());

    }
    //GET /players/ranking/winner: retorna el jugador  amb millor percentatge d’èxit
    @GetMapping ("/players/ranking/winner")
    public ResponseEntity<String> getWinner() {
        return ResponseEntity.ok(serviceJugador.getMayor());

    }

}
