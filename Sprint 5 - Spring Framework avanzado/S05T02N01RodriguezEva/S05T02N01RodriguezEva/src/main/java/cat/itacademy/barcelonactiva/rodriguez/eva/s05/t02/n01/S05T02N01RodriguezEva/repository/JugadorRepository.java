package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.repository;

import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador,Long>{

    Optional<Jugador> findByNombre(String nombre);

    Jugador findByEmail(String email);
}
