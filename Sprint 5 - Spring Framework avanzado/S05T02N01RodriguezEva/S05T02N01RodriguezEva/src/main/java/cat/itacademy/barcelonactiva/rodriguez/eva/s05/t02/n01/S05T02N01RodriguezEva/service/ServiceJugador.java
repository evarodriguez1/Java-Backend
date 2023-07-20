package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.service;

import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Jugador;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Tirada;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.repository.JugadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.schema.Entry;

import javax.transaction.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class ServiceJugador {

    @Autowired
    private final JugadorRepository jugadorRepository;

    public Jugador agregarJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // GET /players/{id}/games: retorna el llistat de jugades per un jugador.
    public List<Tirada> getTiradas(Long id) {
        Optional<Jugador> tiradas = jugadorRepository.findById(id);
        return (List<Tirada>) tiradas.get().getLanzamientos();

    }

    //GET /players/ retorna el llistat de tots  els jugadors del sistema  amb el seu  percentatge mig  d’èxits
    public Double listarJugadoresConExito(List<Jugador> jugadores) {
        double totalPorcentajes = 0;
        double totalJugadores = jugadores.size();

        for(Jugador j : jugadores) {
            totalPorcentajes += j.getPorcentaje_Exito();
        }
        double mejorPorcentaje = totalPorcentajes/totalJugadores;
        return  mejorPorcentaje;
    }
/*
    public Double getRankingExito() {
        List<Jugador> jugadores = jugadorRepository.findAll();
      return
    }


 */
    //GET /players/ranking/loser: retorna el jugador  amb pitjor percentatge d’èxit
    public String getMenor(List<Jugador> jugadores) {
        for (Jugador j:getJugadores()) {
            j.setPorcentaje_Exito(j.porcentajeExito(j.getLanzamientos()));

        }
    }



    //	GET /players/ranking/winner: retorna el  jugador amb  millor percentatge   d’èxit
    public String getMayor() {
        Map<String, Double> listado = listarJugadoresConExito(Jugador);
        List<Entry<String, Double>> list = new ArrayList<>(listado.entrySet());
        list.sort(Entry.comparingByValue());

        return "El jugador con mayor ratio de éxito es: " + list.get(list.size()-1)  ;
    }


    public Optional<Jugador> findJugadorById(Long id_jugador) {
        return jugadorRepository.findById(id_jugador);
    }

    public List<Jugador> getJugadores() {
        return (List<Jugador>) jugadorRepository.findAll();
    }

    public Jugador findById(Long id) {
        return jugadorRepository.getById(id);
    }

    public Jugador saveUser(Jugador user) {
        return jugadorRepository.save(user);
    }

    public Jugador findByEmail(String email) {
        return jugadorRepository.findByEmail(email);
    }

    public String verifyUserData(Jugador jugador) {
        jugador.setDate(new Date());
        Jugador verifyUser;
        verifyUser = jugadorRepository.findByEmail(jugador.getEmail());
        if(verifyUser == null && jugador.getEmail() != null ) {
            if(jugador.getNombre() == null) {
                jugador.setNombre("anonimo");
            }
            return "Created";
        }else if(jugador.getEmail() == null) {
            return "Falta email";
        }else {
            return "User exist";
        }

    }
}
