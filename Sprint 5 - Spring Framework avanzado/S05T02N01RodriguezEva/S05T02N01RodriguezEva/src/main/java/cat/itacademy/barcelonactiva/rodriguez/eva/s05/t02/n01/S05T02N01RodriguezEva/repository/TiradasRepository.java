package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.repository;

import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity.Tirada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TiradasRepository extends JpaRepository<Tirada,Long> {

    ArrayList<Tirada> findByIdUser(Long id);
}
