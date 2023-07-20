package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.service;

import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Jugador;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Tirada;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.repository.TiradasRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ServiceTirada {

    @Autowired
    private final TiradasRepository tiradasRepository;

    public Tirada createGame(Tirada game) {
        return tiradasRepository.save(game);
    }

    //POST /players/{id}/games/ : un jugador específic realitza una tirada  dels daus.
    public String jugarLosDados(Long id) {
        Tirada tiradaNueva = new Tirada();
        return	tiradaNueva.tiraLosDados(id);

    }

    //DELETE /players/{id}/games: elimina les tirades del jugador
    public String borrarTiradas(Long id) {
        tiradasRepository.deleteById(id);
        return "Tiradas eliminadas";

    }



    public boolean verifyGameData(Tirada game) {

        boolean ok = true;
        if(game.getDado1() >= 0 && game.getDado1() <= 7 && game.getDado2() >= 0 && game.getDado2() <= 7) {
            return ok;
        }else {
            ok = false;
            return ok;
        }

    }

    public ArrayList<Tirada> findByUserId(Long id){
        return tiradasRepository.findByIdUser(id);
    }
}
